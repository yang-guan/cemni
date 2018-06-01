package com.huiju.common.sql.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.huiju.module.data.BaseEntity;

/**
 * 系统虚表，专门定义用来写自定义SQL语句
 * 
 * @author：yuhb
 * @date：2016年12月17日 下午22:38:11
 */
@Entity
@Table(name = "D_CN_SQL")
@NamedStoredProcedureQueries({ @NamedStoredProcedureQuery(name = "getCnNum", procedureName = "cemni.getCnNum", parameters = { @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_in_numCode", type = Integer.class), @StoredProcedureParameter(mode = ParameterMode.OUT, name = "v_out_nextStrNum", type = String.class) }) })
public class Sql extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Sql_PK")
    @TableGenerator(name = "Sql_PK", table = "s_pkGenerator", pkColumnName = "PkGeneratorName", valueColumnName = "PkGeneratorValue", pkColumnValue = "Sql_PK", allocationSize = 1)
    private Long id;
}