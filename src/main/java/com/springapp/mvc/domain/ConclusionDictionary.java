package com.springapp.mvc.domain;

import javax.persistence.*;
import javax.persistence.Table;

/**
 * Created by Yurii on 23.02.2016.
 */
@Entity
@Table(name = "conclusion_dictionary", schema = "", catalog = "endo")
public class ConclusionDictionary {
    private int id;
    private String name;

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConclusionDictionary that = (ConclusionDictionary) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    /**
     * Created by Yurii on 27.02.2016.
     */
    public static class Field {
        public static enum TYPES {INT, VARCHAR, DATE, TEXT, FK};

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public TYPES getType() {
            return type;
        }

        public void setType(TYPES type) {
            this.type = type;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public boolean isGeneratedValue() {
            return generatedValue;
        }

        public void setGeneratedValue(boolean generatedValue) {
            this.generatedValue = generatedValue;
        }

        public boolean isVisible() {
            return visible;
        }

        public void setVisible(boolean visible) {
            this.visible = visible;
        }

        private String name;
        private TYPES type;
        private String caption;
        private String value;
        private boolean generatedValue;
        private boolean visible;
        private boolean fk;

        public boolean isFk() {
            return fk;
        }

        public void setFk(boolean fk) {
            this.fk = fk;
        }


        public String getFkTableName() {
            return fkTableName;
        }

        public void setFkTableName(String fkTableName) {
            this.fkTableName = fkTableName;
        }

        public String getFkField() {
            return fkField;
        }

        public void setFkField(String fkField) {
            this.fkField = fkField;
        }

        public String getFkFieldCaption() {
            return fkFieldCaption;
        }

        public void setFkFieldCaption(String fkFieldCaption) {
            this.fkFieldCaption = fkFieldCaption;
        }

        private String fkTableName;
        private String fkField;
        private String fkFieldCaption;

        public Field(String name, TYPES type, String caption, String value, boolean generatedValue, boolean visible) {
            this.name = name;
            this.type = type;
            this.caption = caption;
            this.value = value;
            this.generatedValue = generatedValue;
            this.visible = visible;
            this.fkFieldCaption = null;
            this.fkField = null;
            this.fkTableName = null;
        }

        public void setFk(String fkTableName, String fkField, String fkFieldCaption) {
            this.fkTableName = fkTableName;
            this.fkField = fkField;
            this.fkFieldCaption = fkFieldCaption;
        }
    }

    /**
     * Created by Yurii on 16.03.2016.
     */
    public static class SubTable extends DisinfectantSolution.Table {

        private String relationCaption;
        private String fk;
        public SubTable(DisinfectantSolution.Table t, String relationCaption, String fk){
            super(t.getTableName(), t.getCaption(), t.getTableRepository());
            this.relationCaption = relationCaption;
            this.fk = fk;
        }

        @Override
        public String browseCaptions(){
            String res = "<div class=\"col-sm-12 top-border\">";
            Field f;
            int columnCount = getVisibleCount()+1;
            int columnSize = (int) 12 / columnCount;
            //captions
            for (int i = 0; i < getFields().size(); i++) {
                f = getFields().get(i);
                if (f.isVisible()) {
                    res += "<div class=\"col-sm-" + columnSize + "\">";
                    res += f.getCaption();
                    res += "</div>";
                }
            }
            res += "<div class=\"col-sm-" + columnSize + "\">";
            res += "Дія";
            res += "</div>";
            res += "</div>";
            return res;
        }

        @Override
        public String browseField(String idTable){
            String res = "<div class=\"col-sm-12 top-border\">";
            Field f;
            int columnCount = getVisibleCount();
            int columnSize = (int) 12 / columnCount;
            for (int i = 0; i < getFields().size(); i++) {
                f = getFields().get(i);
                if (f.isVisible()) {
                    res += "<div class=\"col-sm-" + columnSize + "\">";
                    res += "<a href='"+getUrl()+"edit/"+idTable+"/"+getIdFieldValue()+"'>";
                    res += f.getValue();
                    res += "</a>";
                    res += "</div>";
                }
            }
            res += "<div class=\"col-sm-" + columnSize + "\">";
            res += "<a href='"+getUrl()+"browseSubTable/"+idTable+"/"+fk+"'>";
            res += relationCaption;
            res += "</a>";
            res += "</div>";
            res += "</div>";
            return res;
        }

    }
}
