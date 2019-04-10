package org.domain.contract;

import org.domain.StaticEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 合同模板
 */
@Entity
@Table(name = "contract_template")
public class ContractTemplate extends StaticEntity {
    /**
     * 模板ID
     */
    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(length = 32)
    private String templateId;

    /**
     * 模板名称，一般是合同模板文件的名称
     */
    @Column
    private String templateName;

    /**
     * 模板文件的位置(doc文件)
     */
    @Column
    private String templatePath;

    /**
     * 模板的PDF文件位置(pdf文件)
     */
    @Column
    private String templatePdfPath;

    @Override
    public String getId() {
        return this.getTemplateId();
    }

    @Override
    public void setId(String id) {
        this.templateId = id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getTemplatePdfPath() {
        return templatePdfPath;
    }

    public void setTemplatePdfPath(String templatePdfPath) {
        this.templatePdfPath = templatePdfPath;
    }
}
