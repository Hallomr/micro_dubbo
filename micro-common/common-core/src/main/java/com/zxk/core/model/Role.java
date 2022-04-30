package com.zxk.core.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author zxk
 * @since 2022-04-25
 */
@TableName("t_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @TableId(value = "frole_id", type = IdType.ASSIGN_ID)
    private Long froleId;

    /**
     * 租户id
     */
    @TableField("ftenant_id")
    private Long ftenantId;

    /**
     * 角色名称
     */
    @TableField("frole_name")
    private String froleName;

    /**
     * 角色描述
     */
    @TableField("frole_describe")
    private String froleDescribe;

    /**
     * 是否删除，0否，1是，默认0
     */
    @TableField("fis_delete")
    private Integer fisDelete;

    /**
     * 创建时间
     */
    @TableField("fcreate_time")
    private LocalDateTime fcreateTime;

    /**
     * 创建人id
     */
    @TableField("fcreate_id")
    private Long fcreateId;

    /**
     * 修改时间
     */
    @TableField("fmodify_time")
    private LocalDateTime fmodifyTime;

    /**
     * 修改人id
     */
    @TableField("fmodify_id")
    private Long fmodifyId;

    public Long getFroleId() {
        return froleId;
    }

    public void setFroleId(Long froleId) {
        this.froleId = froleId;
    }
    public Long getFtenantId() {
        return ftenantId;
    }

    public void setFtenantId(Long ftenantId) {
        this.ftenantId = ftenantId;
    }
    public String getFroleName() {
        return froleName;
    }

    public void setFroleName(String froleName) {
        this.froleName = froleName;
    }
    public String getFroleDescribe() {
        return froleDescribe;
    }

    public void setFroleDescribe(String froleDescribe) {
        this.froleDescribe = froleDescribe;
    }
    public Integer getFisDelete() {
        return fisDelete;
    }

    public void setFisDelete(Integer fisDelete) {
        this.fisDelete = fisDelete;
    }
    public LocalDateTime getFcreateTime() {
        return fcreateTime;
    }

    public void setFcreateTime(LocalDateTime fcreateTime) {
        this.fcreateTime = fcreateTime;
    }
    public Long getFcreateId() {
        return fcreateId;
    }

    public void setFcreateId(Long fcreateId) {
        this.fcreateId = fcreateId;
    }
    public LocalDateTime getFmodifyTime() {
        return fmodifyTime;
    }

    public void setFmodifyTime(LocalDateTime fmodifyTime) {
        this.fmodifyTime = fmodifyTime;
    }
    public Long getFmodifyId() {
        return fmodifyId;
    }

    public void setFmodifyId(Long fmodifyId) {
        this.fmodifyId = fmodifyId;
    }

    @Override
    public String toString() {
        return "Role{" +
            "froleId=" + froleId +
            ", ftenantId=" + ftenantId +
            ", froleName=" + froleName +
            ", froleDescribe=" + froleDescribe +
            ", fisDelete=" + fisDelete +
            ", fcreateTime=" + fcreateTime +
            ", fcreateId=" + fcreateId +
            ", fmodifyTime=" + fmodifyTime +
            ", fmodifyId=" + fmodifyId +
        "}";
    }
}