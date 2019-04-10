package org.domain.product;

import org.domain.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="attachment")
public class Attachment extends AbstractEntity {

    @Id
    @GenericGenerator(name="systemUUID",strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column
    private String attachmentId;

    /**
     * 附件标题
     */
    @Column
    private String attachmentTitle;

    /**
     * 附件所属组
     */
    @Column
    private String attachmentGroup;
    /**
     * 附件名称
     */
    @Column
    private String attachmentNm;

    /**
     * 附件后缀名
     */
    @Column
    private String attachmentExt;

    /**
     * 附件大小
     */
    @Column
    private Long attachmentSize;

    /**
     * 是否是临时文件（暂未想到用处)
     */
    @Column
    private Integer isTemp = Integer.valueOf(0);

    /**
     * 附件路径
     */
    @Column
    private String attachmentPath;

    /**
     * 附件Hash值
     */
    @Column
    private String attachmentHash;

    @Transient
    private String state;

    @Transient
    private String url;

    @Transient
    private String attachmentUrl;

    @Override
    public void setId(String id) {
        this.attachmentId = id;
    }

    @Override
    public String getId() {
        return this.attachmentId;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getAttachmentTitle() {
        return attachmentTitle;
    }

    public void setAttachmentTitle(String attachmentTitle) {
        this.attachmentTitle = attachmentTitle;
    }

    public String getAttachmentGroup() {
        return attachmentGroup;
    }

    public void setAttachmentGroup(String attachmentGroup) {
        this.attachmentGroup = attachmentGroup;
    }

    public String getAttachmentNm() {
        return attachmentNm;
    }

    public void setAttachmentNm(String attachmentNm) {
        this.attachmentNm = attachmentNm;
    }

    public String getAttachmentExt() {
        return attachmentExt;
    }

    public void setAttachmentExt(String attachmentExt) {
        this.attachmentExt = attachmentExt;
    }

    public Long getAttachmentSize() {
        return attachmentSize;
    }

    public void setAttachmentSize(Long attachmentSize) {
        this.attachmentSize = attachmentSize;
    }

    public Integer getIsTemp() {
        return isTemp;
    }

    public void setIsTemp(Integer isTemp) {
        this.isTemp = isTemp;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public String getAttachmentHash() {
        return attachmentHash;
    }

    public void setAttachmentHash(String attachmentHash) {
        this.attachmentHash = attachmentHash;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    @Transient
    public String getFileNm(String fileName){
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    @Transient
    public String getFileExt(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

}
