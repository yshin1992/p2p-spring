package org.p2p.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class RemoteUtil {

	private static Logger logger=LoggerFactory.getLogger(RemoteUtil.class);
	
	/**Web接口跟路径地址*/
	private static String WEBAPI_ROOT = null;
	
	private static Map<String,String> URL_MAP;
	static{
		DocumentBuilderFactory docBuilderFac = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = docBuilderFac.newDocumentBuilder();
			docBuilder.setEntityResolver(new EntityResolver(){
                public InputSource resolveEntity(String publicId,String sytemId) 
                        throws SAXException, IOException {
                   String dtdFile = getFileName(true);
                   return new InputSource(dtdFile.toString());
                }
            });
			Document doc = docBuilder.parse(getFileName(false));
			doParse(doc);
		} catch (ParserConfigurationException e) {
			logger.error("remote-api.xml解析失败", e.getCause());
		} catch (SAXException e) {
			logger.error("remote-api.xml解析失败", e.getCause());
		} catch (IOException e) {
			logger.error("remote-api.xml解析失败", e.getCause());
		}
	}

	/**
	 * @param flag
	 * @return 返回 remote-config下的文件全名
	 */
	private static String getFileName(boolean flag){
		StringBuffer fileName = new StringBuffer();
		fileName.append(RemoteUtil.class.getResource("/").getPath());
		fileName.append("remote-config");
		fileName.append(System.getProperty("file.separator"));
        if (flag) {
        	fileName.append("remote-api.dtd");
        } else {
        	fileName.append("remote-api.xml");
        }
        return fileName.toString();
	}
	
	/**
	 * 解析remote-api.xml
	 * @param doc
	 */
	private static void doParse(Document doc) {
		URL_MAP = new HashMap<String,String>();
		NodeList apiList = doc.getElementsByTagName("request");
        for (int i=0; i < apiList.getLength(); i++){
            Node request =  apiList.item(i);
            NamedNodeMap attr = request.getAttributes();
            Node keyNode = attr.getNamedItem("id");
            Node valueNode = attr.getNamedItem("url");
            URL_MAP.put(keyNode.getNodeValue().trim(), valueNode.getNodeValue().trim());
        }
	}
	
	/**
	 * 访问远程API返回JSON格式字符串<br/>
	 * 失败返回空串
	 * @param apiId 远程APIID 参见remote-api.xml
	 * @param requestParam 请求参数
	 * @param method 请求类型
	 * @return jsonStr JSON格式字符串
	 * @throws MalformedURLException 
	 */
	private static String submit(String apiId,Map<String,String> requestParam,String method) 
			throws MalformedURLException {
		
		if (StringUtils.isEmpty(WEBAPI_ROOT)) {
			WEBAPI_ROOT = loadAPIRoot();
		}
		String jsonStr = "";
		StringBuffer buffer = new StringBuffer();
		buffer.append(WEBAPI_ROOT);
		buffer.append(URL_MAP.get(apiId));
		String requestStr = buffer.toString();
		if(StringUtils.isEmpty(requestStr)) {
			return jsonStr;
		}
		
		URL url = null;
		String parameterString = null;
		if (CollectionUtils.isEmpty(requestParam)) {
			parameterString = "";
		} else {
			parameterString = createQueryParam(requestParam);
		}
		if ("POST".equalsIgnoreCase(method) ) {
			url = new URL(requestStr);
		} else {
			
			if (!StringUtils.isEmpty(parameterString)) {
				StringBuffer sBuffer = new StringBuffer(requestStr);
				sBuffer.append("?");
				sBuffer.append(parameterString);
				url = new URL(sBuffer.toString());
			} else {
				url = new URL(requestStr);
			}
			
		}
		logger.debug("链接后台api---start：请求路径:{},请求方法:{}", url, method);
		// 判断协议类型 http?https?
		if (StringUtils.startsWithIgnoreCase(requestStr, "https")) {
			jsonStr = doHttps(url, parameterString, method);
		} else {
			jsonStr = doHttp(url,parameterString,method);
		}
		logger.debug("链接后台api---end：api返回结果:{}", jsonStr);
		return jsonStr;
	}
	/**
	 * 以HTTPS方式访问远程远程API
	 * @param url 请求地址
	 * @param parameterString 参数字符串（key=value)
	 * @param method 请求方法
	 * @return
	 */
	private static String doHttps(URL url,String parameterString,String method) {
		TrustManager[] tm = {new RemoteTrustManager()};
		String jsonStr = "";
		try {
			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(new KeyManager[0],  tm, new SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			HttpsURLConnection httpsConn = (HttpsURLConnection)url.openConnection();
			httpsConn.setSSLSocketFactory(ssf);
			httpsConn.setHostnameVerifier(new RemoteHostnameVerifier());
			httpsConn.setRequestMethod(method);
			jsonStr = doConnection(httpsConn,parameterString,method);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e.getCause());
		} catch (KeyManagementException e) {
			logger.error(e.getMessage(), e.getCause());
		} catch (MalformedURLException e) {
			logger.error(e.getMessage(), e.getCause());
		} catch (IOException e) {
			logger.error(e.getMessage(), e.getCause());
		} 
		return jsonStr;
	}
	
	/**
	 * 以HTTP方式访问远程远程API
	 * @param url 请求地址
	 * @param parameterString 参数字符串（key=value)
	 * @param method 请求方法
	 * @return
	 */
	private static String doHttp(URL url,String parameterString,String method) {
		String jsonStr = "";
		InputStream is = null;
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod(method);
			jsonStr = doConnection(conn,parameterString,method);
		} catch (MalformedURLException e) {
			logger.error(e.getMessage(), e.getCause());
		} catch (IOException e) {
			logger.error(e.getMessage(), e.getCause());
		} finally {
			if (is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return jsonStr;
	}
	
	/**
	 * 执行连接
	 * @param conn
	 * @param requestParam
	 * @return
	 */
	private static String doConnection(HttpURLConnection conn,String parameterString,String method) {
		// 绑定请求参数
		InputStream is = null;
		DataOutputStream dos = null;
		String jsonStr = "";
		try {
			conn.setDoInput(true);
			conn.setUseCaches(false); 
			if ("POST".equalsIgnoreCase(method)) {
				conn.setDoOutput(true);
				conn.setInstanceFollowRedirects(false);
				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
				conn.setRequestProperty("Accept-Charset", "utf-8");
				dos = new DataOutputStream(conn.getOutputStream());
				byte[] paramBytes = parameterString.getBytes("utf-8");
				dos.write(paramBytes);
				dos.flush();
			} 
		    conn.connect();
			int responseCode = conn.getResponseCode();
			if (responseCode == 200) {
				is = conn.getInputStream();
				BufferedReader br =  new BufferedReader(new InputStreamReader(is, "UTF-8"));
				String data = null;
				StringBuffer dataBuf = new StringBuffer();
				while ((data = br.readLine())!=null) {
					dataBuf.append(new String(data));
				}
				jsonStr = dataBuf.toString();
				if (logger.isDebugEnabled()) {
					logger.debug("remote json result:" + jsonStr);
				}
			} else {
				jsonStr = "{\"status\":"+responseCode+"}";
				logger.error("remote url error. response code:" + responseCode);
				logger.error("url:" + conn.getURL().getPath());
				logger.error("method:" + conn.getRequestMethod());
				logger.error("request parameter:" + parameterString);
			}
			conn.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("",e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e.getCause());
				}
			}
			if (dos!=null) {
				try{
					dos.close();
				} catch(IOException e) {
					logger.error(e.getMessage(), e.getCause());
				}
			}
		}
		return jsonStr;
	}
	
	
	/**
	 * 取API地址
	 * 开发阶段返回公网接口地址
	 * */
	private static String loadAPIRoot() {
		//后期从数据库中查取
		return "http://127.0.0.1:8080/apis/";
	}
	
	/**
	 * 创建API访问参数
	 * @param requestParam 参数KEY
	 * @return
	 */
	private static String createQueryParam(Map<String,String> requestParam) {
		StringBuffer param = new StringBuffer();
		Set<Entry<String,String>> set = requestParam.entrySet();
		Iterator<Entry<String,String>> it = set.iterator();
		while(it.hasNext()) {
			Entry<String,String> entry = it.next();
			param.append(entry.getKey());
			param.append("=");
			param.append(entry.getValue());
			if (it.hasNext()) {
				param.append("&");
			}
		}
		return param.toString();
	}
	
	
	/**
	 * 以POST方式调用远程服务<br/>
	 * 失败返回空串
	 * @param apiId  远程APIID 参见remote-api.xml
	 * @param requestParam 请求参数
	 * @return JSON格式字符串
	 */
	public static String post(String apiId,Map<String,String> requestParam){
		try {
			return submit(apiId, requestParam, "POST");
		} catch (MalformedURLException e) {
			logger.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 以GET方式访问远程服务<br/>
	 * 失败返回空串
	 * @param apiId 远程APIID 参见remote-api.xml
	 * @param requestParam 请求参数
	 * @return JSON格式字符串
	 */
	public static String get(String apiId,Map<String, String> requestParam){
		try {
			return submit(apiId, requestParam, "GET");
		} catch (MalformedURLException e) {
			logger.error(e.getMessage(), e.getCause());
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 接收远程API返回的文件输入流
	 * @param apiId APIID remote-api.xml
	 * @param requestParam API参数
	 * @return bis 文件输入流
	 * @throws IOException
	 */
	public static BufferedInputStream receiveStream(String apiId,Map<String, String> requestParam) throws IOException {
		if (StringUtils.isEmpty(WEBAPI_ROOT)) {
			WEBAPI_ROOT = loadAPIRoot();
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append(WEBAPI_ROOT);
		buffer.append(URL_MAP.get(apiId));
		String requestStr = buffer.toString();
		URL url = null;
		String parameterString = null;
		if (CollectionUtils.isEmpty(requestParam)) {
			parameterString = "";
		} else {
			parameterString = createQueryParam(requestParam);
		}
		if (!StringUtils.isEmpty(parameterString)) {
			StringBuffer sBuffer = new StringBuffer(requestStr);
			sBuffer.append("?");
			sBuffer.append(parameterString);
			url = new URL(sBuffer.toString());
		} else {
			url = new URL(requestStr);
		}
		BufferedInputStream bis = null;
		if (StringUtils.startsWithIgnoreCase(requestStr, "https")){
			//https
			TrustManager[] tm = {new RemoteTrustManager()};
			SSLContext sslContext;
			try {
				sslContext = SSLContext.getInstance("SSL");
				sslContext.init(new KeyManager[0],  tm, new SecureRandom());
				SSLSocketFactory ssf = sslContext.getSocketFactory();
				HttpsURLConnection httpsConn = (HttpsURLConnection)url.openConnection();
				httpsConn.setSSLSocketFactory(ssf);
				httpsConn.setHostnameVerifier(new RemoteHostnameVerifier());
				httpsConn.setRequestMethod("GET");
				httpsConn.connect();
				bis = new BufferedInputStream (httpsConn.getInputStream());
			} catch (NoSuchAlgorithmException e) {
				logger.error(e.getMessage(), e);
				throw new IOException(e.getMessage());
			} catch (KeyManagementException e) {
				logger.error(e.getMessage(), e);
				throw new IOException(e.getMessage());
			}
			
		} else {
			//http
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			bis = new BufferedInputStream (conn.getInputStream());
		}
		return bis;
			
	}
	
	private static class RemoteTrustManager implements TrustManager,X509TrustManager {

		@Override
		public void checkClientTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[]{};
		}
	}
	
	private static class RemoteHostnameVerifier implements HostnameVerifier {
		@Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}
