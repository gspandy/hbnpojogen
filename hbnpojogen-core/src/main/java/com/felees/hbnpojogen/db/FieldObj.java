package com.felees.hbnpojogen.db;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import com.felees.hbnpojogen.obj.Clazz;
import com.felees.hbnpojogen.obj.PropertyObj;

/** A representation of the fields as seen from a DB point of view.
 * @author wallacew
 *
 */
public class FieldObj implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -1521632621587378270L;
	/** fieldname. */
	private String name;
    /** Set if renamed. */
    private String alias;
    /** Set if renamed (inverse link). */
    private String aliasInverse;

	/** If true, the field has a default value set in the database. */
	@SuppressWarnings("unused")
	private boolean defaultValue;
	/** sql type. */
	private int fieldType;
	/** length of field. */
	private int length;
	/** field is marked as nullable. */
	private boolean nullable;
	/** field is marked as autoinc. */
	private boolean autoInc;
	/** for enumerations. */
	private String enumName;
	/** the file we've written the list to. */
	private String enumFilename;
	/** for DECIMAL types.  */
	private int precision;
	/** for DECIMAL types.  */
	private int scale;
	/** If true, this field is being considered as an enum due to config file settings */
	private boolean fakeEnum;
	/** is this is a primary key. */
	private boolean primaryKey;
	/** a list of allowed enum values. */
	private String[] enumValues;
	/** a map of "other" enum values. */
    private Map<String, Object> enumOtherCols = new TreeMap<String, Object>();
    /** Convenience linking. */
	private Clazz clazz;
	/** this field is marked as unsigned. */
	private boolean fieldTypeUnsigned;
	/** this is a foreign key (possibly also a primary key). */
	private boolean foreignKey;
	/** Link to the table object this is coming from. */
	private TableObj tableObj;
	/** if foreignKey is true, this is the link to the external foreign table's col. */
	private FieldObj foreignColumn;
	/** link to the target object (convenience). */
	private PropertyObj property;

	public boolean isAliased(){
		return this.alias != null;
	}
	@Override
    public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.name + " ");

		if (this.primaryKey) {
			sb.append(" PK ");
		}
		sb.append(" ");
		if (this.isAliased()){
			sb.append(this.alias);
		}
		if (this.foreignKey) {
			sb.append(" FK ");
		}

		return sb.toString();
	}

	/** Returns true if field is a primary foreign key.
	 * @return true if field is a primary foreign key
	 */
	public boolean isPFK() {
		return this.primaryKey && this.foreignKey;
	}

	public boolean isPFKAlone() {
		boolean pfk = this.primaryKey && this.foreignKey;

		if (pfk) {
			for (FieldObj f: this.getTableObj().getFields().values()){
				if (f != this && f.isPFK()){
					pfk = false;
				}
			}
		}

			return pfk;
	}

	/** Returns true if field is an enum.
	 * @return true/false
	 */
	public boolean isEnum() {
		return this.enumName != null;
	}

	/** Getter for fieldType.
	 * @return fieldType
	 */
	public int getFieldType() {
		return this.fieldType;
	}

	/** Setter for fieldtype.
	 * @param fieldtype
	 */
	public void setFieldType(int fieldtype) {
		this.fieldType = fieldtype;
	}

	/** Getter for length.
	 * @return this.length
	 */
	public int getLength() {
		return this.length;
	}

	/** Setter for length.
	 * @param length  to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/** Getter for nullable.
	 * @return this.nullable
	 */
	public boolean isNullable() {
		return this.nullable;
	}

	/** Setter for Nullable.
	 * @param isNullable  to set
	 */
	public void setNullable(boolean isNullable) {
		this.nullable = isNullable;
	}

	/** Setter for isAutoInc.
	 * @param isAutoInc  to set
	 */
	public void setAutoInc(boolean isAutoInc) {
		this.autoInc = isAutoInc;

	}

	/** Getter for autoInc.
	 * @return  this.autoInc
	 */
	public boolean isAutoInc() {
		return this.autoInc;
	}

	/** Getter for enumName.
	 * @return this.enumName
	 */
	public String getEnumName() {
		return this.enumName;
	}

	/** Setter for enumName.
	 * @param enumName to set
	 */
	public void setEnumName(String enumName) {
		this.enumName = enumName;
	}

	/** Getter for enumFilename.
	 * @return  this.enumFilename
	 */
	public String getEnumFilename() {
		return this.enumFilename;
	}

	/** Setter for  enumFilename.
	 * @param enumFilename to set
	 */
	public void setEnumFilename(String enumFilename) {
		this.enumFilename = enumFilename;
	}

	/** Getter for primaryKey.
	 * @return this.primaryKey
	 */
	public boolean isPrimaryKey() {
		return this.primaryKey;
	}

	/** Setter for PrimaryKey.
	 * @param isPrimaryKey t/f
	 */
	public void setPrimaryKey(boolean isPrimaryKey) {
		this.primaryKey = isPrimaryKey;
	}

	/** Getter for enumValues.
	 * @return this.enumValues
	 */
	public String[] getEnumValues() {
		return this.enumValues;
	}

	/** Setter for enumValues.
	 * @param enumValues to set
	 */
	public void setEnumValues(String[] enumValues) {
		this.enumValues = enumValues;
	}

	/** Getter for name.
	 * @return this.name
	 */
	public String getName() {
		return this.name;
	}

	/** Setter for name.
	 * @param name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** Setter for class Object.
	 * @param clazz clazz to set
	 */
	public void setClazz(Clazz clazz) {
		this.clazz = clazz;

	}

	/** Getter for clazz field.
	 * @return this.clazz
	 */
	public Clazz getClazz() {
		return this.clazz;
	}

	/** Set ft unsigned.
	 * @param unsigned t/f
	 */
	public void setFieldTypeUnsigned(boolean unsigned) {
		this.fieldTypeUnsigned = unsigned;
	}

	/** Getter for fieldTypeUnsigned.
	 * @return this.fieldTypeUnsigned
	 */
	public boolean isFieldTypeUnsigned() {
		return this.fieldTypeUnsigned;
	}

	/** Getter for foreignKey.
	 * @return the foreignKey
	 */
	public final boolean isForeignKey() {
		return this.foreignKey;
	}

	/** Setter for foreignKey.
	 * @param foreignKey the foreignKey to set
	 */
	public final void setForeignKey(boolean foreignKey) {
		this.foreignKey = foreignKey;
	}

	/** Getter for tableObj.
	 * @return the tableObj
	 */
	public final TableObj getTableObj() {
		return this.tableObj;
	}

	/** Setter for tableObj.
	 * @param tableObj the tableObj to set
	 */
	public final void setTableObj(TableObj tableObj) {
		this.tableObj = tableObj;
	}

	/** Getter for foreignColumn.
	 * @return the foreignColumn
	 */
	public final FieldObj getForeignColumn() {
		return this.foreignColumn;
	}

	/** Setter for foreignColumn.
	 * @param foreignColumn the foreignColumn to set
	 */
	public final void setForeignColumn(FieldObj foreignColumn) {
		this.foreignColumn = foreignColumn;
	}

	/** Getter for property.
	 * @return the property
	 */
	public final PropertyObj getProperty() {
		return this.property;
	}

	/** Setter for property.
	 * @param property the property to set
	 */
	public final void setProperty(PropertyObj property) {
		this.property = property;
	}

	/** Getter for precision.
	 * @return the precision
	 */
	public final int getPrecision() {
		return this.precision;
	}

	/** Setter for precision.
	 * @param precision the precision to set
	 */
	public final void setPrecision(int precision) {
		this.precision = precision;
	}

	/** Getter for scale.
	 * @return the scale
	 */
	public final int getScale() {
		return this.scale;
	}

	/** Setter for scale.
	 * @param scale the scale to set
	 */
	public final void setScale(int scale) {
		this.scale = scale;
	}

	/**
	 * @return t/f
	 */
	public boolean isFakeEnum() {
		return fakeEnum;
	}

	/**
	 * @param fakeEnum
	 */
	public void setFakeEnum(boolean fakeEnum) {
		this.fakeEnum = fakeEnum;
	}

	/**
	 * @return t/f
	 */
	public boolean isDefaultValue() {
		// enable this when hibernate actually makes it work!
		return false; // defaultValue;
	}

	/**
	 * @param defaultValue
	 */
	public void setDefaultValue(boolean defaultValue) {
		this.defaultValue = defaultValue;
	}
	/**
	 * @return the alias
	 */
	public final String getAlias() {
		return alias;
	}
	/**
	 * @param alias the alias to set
	 */
	public final void setAlias(String alias) {
		this.alias = alias;
	}

    /**
     * Gets
     *
     * @return
     */
    public String getAliasInverse() {
        return this.aliasInverse;
    }



    /**
     * Sets
     *
     * @param aliasInverse
     */
    public void setAliasInverse(String aliasInverse) {
        this.aliasInverse = aliasInverse;
    }

    /**
     * Gets
     * 
     * @return
     */
    public Map<String, Object> getEnumOtherCols() {
        return this.enumOtherCols;
    }



    /**
     * Sets
     * 
     * @param enumOtherCols
     */
    public void setEnumOtherCols(Map<String, Object> enumOtherCols) {
        this.enumOtherCols = enumOtherCols;
    }




}
