package org.business.util;

import java.util.ArrayList;
import java.util.List;

import org.dao.hibernate.system.ResourceDao;
import org.domain.system.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class SysFuncCache {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private ResourceDao resourceDao;
    
    public void cacheSysFunc(List<Resource> resources, String userCd){
        cacheManager.getCache("sysFunc").put("sysFunc_"+userCd,resources);
    }

    @SuppressWarnings("unchecked")
	public List<Resource> getAllResources(String userCd){
    	ValueWrapper valueWrapper = cacheManager.getCache("sysFunc").get("sysFunc_"+userCd);
    	if(valueWrapper == null || valueWrapper.get() == null){
    		return null;
    	}
        return (List<Resource>)valueWrapper.get();
    }

    /**
     * 根据当前资源节点获取其父节点路径
     * @param leaf
     * @param userCd
     * @return
     */
    public List<Resource> getResourcePath(Resource leaf,String userCd){
        List<Resource> res  = new ArrayList<>();
        if(null == leaf){
        	return res;
        }
        if(leaf.getParent() == null){
            res.add(leaf);
        }else{
            List<Resource> tmp  = new ArrayList<>();
            tmp.add(leaf);
            List<Resource> resources = getAllResources(userCd);
            Resource parent = null;
            while((parent = findParent(leaf,resources)) != null){
            	tmp.add(parent);
            	leaf = parent;
            }

            for(int i=tmp.size()-1;i>=0;i--){
                res.add(tmp.get(i));
            }
        }
        return res;
    }

    private Resource findParent(Resource r , List<Resource> res){
    	if(r.getParent()==null)
    		return null;
    	Resource parent = null;
    	for(Resource tm : res){
    		if(tm.getResourceId().equals(r.getParent().getId())){
    			parent = tm;
    			break;
    		}
    	}
    	return parent;
    }
    
    /**
     * 清除缓存
     * @param userCd
     */
    public void clear(String userCd){
        cacheManager.getCache("sysFunc").evict("sysFunc_"+userCd);
    }

    /**
     * 获取当前资源的祖先元素
     * @param url
     * @param userCd
     * @return
     */
    @SuppressWarnings("unchecked")
	public List<Resource> getResourcePath(String url,String userCd){
    	ValueWrapper valueWrapper = cacheManager.getCache("sysFunc").get("sysFunc_parent_"+url);
    	if(valueWrapper != null && valueWrapper.get() != null){
    		return (List<Resource>)valueWrapper.get();
    	}
    	Resource resource = resourceDao.findByUrl(url);
    	List<Resource> resourcePath = getResourcePath(resource,userCd);
    	cacheManager.getCache("sysFunc").put("sysFunc_parent_"+url, resourcePath);
    	return resourcePath;
    }
    
    /**
     * 获取当前资源所有的子元素
     * @param resource
     * @param userCd
     * @return
     */
    public List<Resource> getChildResource(Resource resource,String userCd){
    	List<Resource> resources = getAllResources(userCd);
    	List<Resource> childs = new ArrayList<Resource>();
    	for(Resource r : resources){
    		if(resource.getId().equals(r.getPid())){
    			childs.add(r);
    		}
    	}
    	return childs;
    }
    
    /**
     * 刷新用户的权限集合
     * @param userCd
     */
    public void refresh(String userCd){
    	List<Resource> resList = getAllResources(userCd);
    	if(null == resList || resList.isEmpty()){
    		return;//当前用户尚未登录，缓存中没有权限记录的情况下，则无需更新
    	}
    	clear(userCd);//先清除缓存的权限
    	List<Resource> resources = resourceDao.findByUser(userCd);
    	cacheSysFunc(resources,userCd);
    }
    
}
