package com.springapp.mvc.domain;

import com.springapp.mvc.repository.TableRepository;

import javax.persistence.*;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Yurii on 04.01.2016.
 */
@Entity
@Table(name = "disinfectant_solution", schema = "", catalog = "endo")
public class DisinfectantSolution {
    private int id;
    private String name;
    private float size;
    private Collection<Revisions> revisionsesById;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    @Id
    @GeneratedValue
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

    @Basic
    @Column(name = "size")
    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DisinfectantSolution that = (DisinfectantSolution) o;

        if (id != that.id) return false;
        if (Float.compare(that.size, size) != 0) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (size != +0.0f ? Float.floatToIntBits(size) : 0);
        return result;
    }

    @OneToMany(mappedBy = "disinfectantSolutionByDisinfectantSolutionId")
    public Collection<Revisions> getRevisionsesById() {
        return revisionsesById;
    }

    public void setRevisionsesById(Collection<Revisions> revisionsesById) {
        this.revisionsesById = revisionsesById;
    }

    /**
     * Created by Yurii on 27.02.2016.
     */


    public static class Table {

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public ArrayList<ConclusionDictionary.Field> getFields() {
            return fields;
        }

        public void setFields(ArrayList<ConclusionDictionary.Field> fields) {
            this.fields = fields;
        }

        public String getIdFieldName() {
            return idFieldName;
        }

        public void setIdFieldName(String idFieldName) {
            this.idFieldName = idFieldName;
        }

        private String tableName;
        private String caption;
        private ArrayList<ConclusionDictionary.Field> fields;
        private String idFieldValue;
        private TableRepository tableRepository;
        private static String url = "http://localhost:8080/";
        private ArrayList<Method> onBeforeInsert;
        private String idFieldName;
        private boolean mainTemplateVisible = false;
        private String mainTemplateCaption;
        private String mainTemplateTableId;
        private String mainTemplateFieldName;
        private String mainTemplateRelationFieldName;

        public String getMainTemplateRelationFieldName() {
            return mainTemplateRelationFieldName;
        }

        public void setMainTemplateRelationFieldName(String mainTemplateRelationFieldName) {
            this.mainTemplateRelationFieldName = mainTemplateRelationFieldName;
        }


        public String getMainTemplateTableId() {
            return mainTemplateTableId;
        }

        public void setMainTemplateTableId(String mainTemplateTableId) {
            this.mainTemplateTableId = mainTemplateTableId;
        }

        public String getMainTemplateFieldName() {
            return mainTemplateFieldName;
        }

        public void setMainTemplateFieldName(String mainTemplateFieldName) {
            this.mainTemplateFieldName = mainTemplateFieldName;
        }



        public String getMainTemplateCaption() {
            return mainTemplateCaption;
        }

        public void setMainTemplateCaption(String mainTemplateCaption) {
            this.mainTemplateCaption = mainTemplateCaption;
        }


        public boolean isMainTemplateVisible() {
            return mainTemplateVisible;
        }

        public void setMainTemplateVisible(boolean mainTemplateVisible) {
            this.mainTemplateVisible = mainTemplateVisible;
        }


        public String getIdFieldValue() {
            return idFieldValue;
        }

        public void setIdFieldValue(String idFieldValue) {
            this.idFieldValue = idFieldValue;
        }


        public TableRepository getTableRepository() {
            return tableRepository;
        }

        public void setTableRepository(TableRepository tableRepository) {
            this.tableRepository = tableRepository;
        }


        public static String getUrl() {
            return url;
        }

        public static void setUrl(String url) {
            Table.url = url;
        }


        public Table(String name, String caption, TableRepository tableRepository) {
            this.tableName = name;
            this.caption = caption;
            this.fields = new ArrayList<ConclusionDictionary.Field>();
            this.tableRepository = tableRepository;
            this.idFieldName = null;
            this.idFieldValue = null;
            this.onBeforeInsert = new ArrayList<Method>();
        }

        public Table() {
        }

        public void setMainTemplate(String mainTemplateCaption, String mainTemplateRelationFieldName,
                                    String mainTemplateTableId, String mainTemplateFieldName){
            this.mainTemplateVisible = true;
            this.mainTemplateCaption = mainTemplateCaption;
            this.mainTemplateTableId = mainTemplateTableId;
            this.mainTemplateFieldName = mainTemplateFieldName;
            this.mainTemplateRelationFieldName = mainTemplateRelationFieldName;
        }

        public void addBeforeListener(Method m){
            onBeforeInsert.add(m);
        }

        public ConclusionDictionary.Field getFieldByName(String name){
            for(ConclusionDictionary.Field f : fields){
                if(f.getName().equals(name))
                    return f;
            }
            return null;
        }

        boolean func( Method m, Table table)
        {
            try
            {
                Class c = m.getDeclaringClass();
                Object obj = c.newInstance();
                //m.setAccessible(true);
                Object[] args = new Object[]{table};
                Boolean r = (Boolean)m.invoke(obj, args);
                return r;
            }
            catch (Exception x) {
                System.out.println("error " + x.getMessage());
                return false;
            }
        }

        public void addField(ConclusionDictionary.Field field) {
            this.fields.add(field);
            if (field.isGeneratedValue() && this.idFieldName == null) {
                this.idFieldName = field.getName();
            }
        }

        public String browse(String id) {
            String res = "";
            res += browseCaptions();
            res += browseFields(id);
            return res;
        }

        public String browse(String id, String fieldName, String fieldValue, String previousTableId){
            String res = "";
            res += browseCaptions(previousTableId);
            res += browseFields(id, fieldName, fieldValue);
            return res;
        }

        public String browseCaptions() {
            String res = "<div class=\"col-sm-12 top-border\">";
            ConclusionDictionary.Field f;
            int columnCount = getVisibleCount();
            if(mainTemplateVisible)
                columnCount += 1;
            int columnSize = (int) 12 / columnCount;
            //captions
            for (int i = 0; i < fields.size(); i++) {
                f = fields.get(i);
                if (f.isVisible()) {
                    res += "<div class=\"col-sm-" + columnSize + "\">";
                    res += f.getCaption();
                    res += "</div>";
                }
            }
            if(mainTemplateVisible){
                res += "<div class=\"col-sm-" + columnSize + "\">";
                res += "Дія";
                res += "</div>";
            }
            res += "</div>";
            return res;
        }

        public String browseCaptions(String previousTableId) {
            String res = "<a href='"+url+"browse/"+previousTableId+"'><- Назад</a>";
            res += "<div class=\"col-sm-12 top-border\">";
            ConclusionDictionary.Field f;
            int columnCount = getVisibleCount();
            if(mainTemplateVisible)
                columnCount += 1;
            int columnSize = (int) 12 / columnCount;
            //captions
            for (int i = 0; i < fields.size(); i++) {
                f = fields.get(i);
                if (f.isVisible()) {
                    res += "<div class=\"col-sm-" + columnSize + "\">";
                    res += f.getCaption();
                    res += "</div>";
                }
            }
            if(mainTemplateVisible){
                res += "<div class=\"col-sm-" + columnSize + "\">";
                res += "Дія";
                res += "</div>";
            }
            res += "</div>";
            return res;
        }

        public String browseField(String idTable) {
            String res = "<div class=\"col-sm-12 top-border\">";
            ConclusionDictionary.Field f;
            int columnCount = getVisibleCount();
            if(mainTemplateVisible)
                columnCount += 1;
            int columnSize = (int) 12 / columnCount;
            for (int i = 0; i < fields.size(); i++) {
                f = fields.get(i);
                if (f.isVisible()) {
                    res += "<div class=\"col-sm-" + columnSize + "\">";
                    res += "<a href='"+url+"edit/"+idTable+"/"+idFieldValue+"'>";
                    res += f.getValue();
                    res += "</a>";
                    res += "</div>";
                }
            }
            if(mainTemplateVisible){
                res += "<div class=\"col-sm-" + columnSize + "\">";
                res += "<a href='"+url+"browse/"+mainTemplateTableId+"/fieldName/" + mainTemplateRelationFieldName+
                        "/fieldValue/"+getFieldByName(mainTemplateFieldName).getValue()+"/previousTableId/"+idTable+"'>";
                res += mainTemplateCaption;
                res += "</a>";
                res += "</div>";
            }
            res += "</div>";
            return res;
        }

        public String browseFields(String id) {
            String res = "";
            List list = select();
            for (int i = 0; i < list.size(); i++) {
                initFields((Object[]) list.get(i));
                res += browseField(id);
            }
            return res;
        }

        public String browseFields(String id, String fieldName, String fieldValue) {
            String res = "";
            List list = select(fieldName, fieldValue);
            for (int i = 0; i < list.size(); i++) {
                initFields((Object[]) list.get(i));
                res += browseField(id);
            }
            return res;
        }

        public Integer getVisibleCount() {
            int res = 0;
            for (int i = 0; i < fields.size(); i++) {
                if (fields.get(i).isVisible()) {
                    res++;
                }
            }
            return res;
        }

        public List select() {
            String sql = "SELECT ";
            String where = "";
            ArrayList<String> from = new ArrayList<String>();
            for (int i = 0; i < fields.size(); i++) {
                if(fields.get(i).getFkTableName() != null) {
                    sql += fields.get(i).getFkTableName()+"."+fields.get(i).getFkFieldCaption()+", ";
                    where += tableName+"."+fields.get(i).getName() + " = " +
                            fields.get(i).getFkTableName()+"."+fields.get(i).getFkField() + " and ";
                    if(!from.contains(fields.get(i).getFkTableName()))
                        from.add(fields.get(i).getFkTableName());
                }else {
                    sql += tableName + "." + fields.get(i).getName() + ", ";
                }
            }
            sql = sql.substring(0, sql.length() - 2);
            sql += " FROM " + tableName;
            for(String s : from){
                sql += ", "+s;
            }
            if(where.length() > 0) {
                where = where.substring(0, where.length() - 4);
                sql += " WHERE " + where;
            }
            System.out.println(sql);
            return this.tableRepository.makeSql(sql);
        }

        public List select(String fieldName, String fieldValue) {
            String sql = "SELECT ";
            String where = "";
            ArrayList<String> from = new ArrayList<String>();
            for (int i = 0; i < fields.size(); i++) {
                if(fields.get(i).getFkTableName() != null) {
                    sql += fields.get(i).getFkTableName()+"."+fields.get(i).getFkFieldCaption()+", ";
                    where += tableName+"."+fields.get(i).getName() + " = " +
                            fields.get(i).getFkTableName()+"."+fields.get(i).getFkField() + " and ";
                    if(!from.contains(fields.get(i).getFkTableName()))
                        from.add(fields.get(i).getFkTableName());
                }else {
                    sql += tableName + "." + fields.get(i).getName() + ", ";
                }
            }
            sql = sql.substring(0, sql.length() - 2);
            sql += " FROM " + tableName;
            for(String s : from){
                sql += ", "+s;
            }
            if(where.length() > 0) {
                where = where.substring(0, where.length() - 4);
                sql += " WHERE " + where + " and " + fieldName + " = "+ fieldValue;
            }else {
                sql += " WHERE " + fieldName + " = " + fieldValue;
            }
            System.out.println(sql);
            return this.tableRepository.makeSql(sql);
        }

        public void initFields(Object[] objects) {
            int j = 0;
            for (int i = 0; i < fields.size(); i++) {
                if(objects[j] == null){
                    fields.get(i).setValue("");
                    if (fields.get(i).isGeneratedValue())
                        this.idFieldValue = "";
                }
                else {
                    System.out.println(fields.get(i).getName() + " : " + objects[j]);
                    fields.get(i).setValue(objects[j].toString());
                    if (fields.get(i).isGeneratedValue())
                        this.idFieldValue = objects[j].toString();
                }
                j++;
            }
        }

        public Object selectById(String id) {
            String sql = "SELECT ";
            for (int i = 0; i < fields.size(); i++) {
                    sql += tableName + "." + fields.get(i).getName() + ", ";
            }
            sql = sql.substring(0, sql.length() - 2);
            sql += " FROM " + tableName;
            sql += " WHERE " + idFieldName + " = " + id;
            return this.tableRepository.makeSql(sql).get(0);
        }

        public String edit(String idTable, String id) {
            String res = "<form method=\"post\" action=\""+url+"execEdit\\" + idTable + "\\"+ id+
                    "\">";
            initFields((Object[]) selectById(id));
            for (int i = 0; i < fields.size(); i++) {
                res += "<div class=\"col-sm-4\">\n" +
                        "<label>";
                if (fields.get(i).isVisible())
                    res += fields.get(i).getCaption();
                res += "</label>\n" +
                        "</div>";
                switch (fields.get(i).getType()) {
                    case VARCHAR:
                        res += "<div class=\"col-sm-8\">\n" +
                                "<input name=\"" +
                                fields.get(i).getName() +
                                "\" class='input-text'" +
                                "value='" + fields.get(i).getValue() +
                                "'";
                        if (!fields.get(i).isVisible())
                            res += " type='hidden'";
                        res += "/>\n" +
                                "<label>" +
                                "<form:errors path=\"" +
                                fields.get(i).getName() +
                                "\"></form:errors>" +
                                "</label>\n" +
                                "</div>";
                        break;
                    case INT:
                        res += "<div class=\"col-sm-8\">\n" +
                                "<input name=\"" +
                                fields.get(i).getName() +
                                "\" class='input-text'" +
                                "value='" + fields.get(i).getValue() +
                                "'";
                        if (!fields.get(i).isVisible())
                            res += " type='hidden'";
                        res += "/>\n" +
                                "<label>" +
                                "<form:errors path=\"" +
                                fields.get(i).getName() +
                                "\"></form:errors>" +
                                "</label>\n" +
                                "</div>";
                        break;
                    case DATE:
                        res += "<div class=\"col-sm-8\">\n" +
                                "<input type=\"date\" name=\"" +
                                fields.get(i).getName() +
                                "\" class=\"input-text\"" +
                                "value='" + fields.get(i).getValue() +
                                "'";
                        if (!fields.get(i).isVisible())
                            res += " type='hidden'";
                        res += "/>\n" +
                                "<label>" +
                                "<form:errors path=\"" +
                                fields.get(i).getName() +
                                "\"></form:errors>" +
                                "</label>\n" +
                                "</div>";
                        break;
                    case FK:
                            List list = tableRepository.makeSql("select " + fields.get(i).getFkField() + ", " +
                                    fields.get(i).getFkFieldCaption() + " From " + fields.get(i).getFkTableName());
                            res += "<div class=\"col-sm-8\">\n" +
                                    "<select name='" + fields.get(i).getName() + "'>";
                            for (int k = 0; k < list.size(); k++) {
                                Object[] obj = (Object[]) list.get(k);
                                res += "<option value='" + obj[0] + "'>" + obj[1] + "</option>";
                            }
                            res += "</select></div>";
                        break;
                    default:
                        break;
                }
            }
            res += "<input type=\"submit\" value=\"Зберегти\" class=\"button col-sm-6\">";
            res += "</form:form>";
            return res;
        }

        public void execEdit(HttpServletRequest request, String id){
            for(int i = 0; i < fields.size(); i++){
                fields.get(i).setValue(request.getParameter(fields.get(i).getName()));
            }
            update(id);
        }

        public void update(String id){
            String res = "UPDATE " + this.tableName;
            res += " SET ";
            for(int i = 0; i < fields.size(); i++){
                if(!fields.get(i).isGeneratedValue())
                    res += fields.get(i).getName() + " = '" + fields.get(i).getValue() + "', ";
            }
            res = res.substring(0, res.length() - 2);
            res += " WHERE " + idFieldName + " = " + id;
            System.out.println(res);
            this.tableRepository.makeUpdate(res);
        }

        public String insert(String idTable){
            String res = "<form method=\"post\" action=\""+url+"execInsert\\" + idTable +
                    "\">";
            for (int i = 0; i < fields.size(); i++) {
                res += "<div class='col-sm-12'><div class=\"col-sm-4\">\n" +
                        "<label>";
                if (fields.get(i).isVisible())
                    res += fields.get(i).getCaption();
                res += "</label>\n" +
                        "</div>";
                System.out.println(fields.get(i).getName() + " - " + fields.get(i).getType());
                switch (fields.get(i).getType()) {
                    case VARCHAR:
                        res += "<div class=\"col-sm-8\">\n" +
                                "<input name=\"" +
                                fields.get(i).getName() +
                                "\" class='input-text'";
                        if (!fields.get(i).isVisible())
                            res += " type='hidden'";
                        res += "/>\n" +
                                "<label>" +
                                "<form:errors path=\"" +
                                fields.get(i).getName() +
                                "\"></form:errors>" +
                                "</label>\n" +
                                "</div>";
                        break;
                    case INT:
                        res += "<div class=\"col-sm-8\">\n" +
                                "<input name=\"" +
                                fields.get(i).getName() +
                                "\" class='input-text'";
                        if (!fields.get(i).isVisible())
                            res += " type='hidden'";
                        res += "/>\n" +
                                "<label>" +
                                "<form:errors path=\"" +
                                fields.get(i).getName() +
                                "\"></form:errors>" +
                                "</label>\n" +
                                "</div>";
                        break;
                    case DATE:
                        res += "<div class=\"col-sm-8\">\n" +
                                "<input type=\"date\" name=\"" +
                                fields.get(i).getName() +
                                "\" class=\"input-text\"";
                        if (!fields.get(i).isVisible())
                            res += " type='hidden'";
                        res += "/>\n" +
                                "<label>" +
                                "<form:errors path=\"" +
                                fields.get(i).getName() +
                                "\"></form:errors>" +
                                "</label>\n" +
                                "</div>";
                        break;
                    case FK:
                            List list = tableRepository.makeSql("select " + fields.get(i).getFkField() + ", " +
                                    fields.get(i).getFkFieldCaption() + " From " + fields.get(i).getFkTableName());
                            res += "<div class=\"col-sm-8\">\n" +
                                    "<select name='" + fields.get(i).getName() + "'>";
                            for (int k = 0; k < list.size(); k++) {
                                Object[] obj = (Object[]) list.get(k);
                                res += "<option value='" + obj[0] + "'>" + obj[1] + "</option>";
                            }
                            res += "</select></div>";
                        break;
                    default:
                        break;
                }
                res += "</div>";
            }
            res += "<input type=\"submit\" value=\"Добавити\" class=\"button col-sm-6\">";
            res += "</form:form>";
            return res;
        }

        public String insert(String idTable, String fieldValue, String url1){
            String res = "<form method=\"post\" action=\""+url+"execInsert1\\" + idTable + "\">";
            res += "<input type='hidden' value='"+url1+"' name='url'>";
            for (int i = 0; i < fields.size(); i++) {
                res += "<div class='col-sm-12'><div class=\"col-sm-4\">\n" +
                        "<label>";
                if (fields.get(i).isVisible())
                    res += fields.get(i).getCaption();
                res += "</label>\n" +
                        "</div>";
                System.out.println(fields.get(i).getName() + " - " + fields.get(i).getType());
                switch (fields.get(i).getType()) {
                    case VARCHAR:
                        res += "<div class=\"col-sm-8\">\n" +
                                "<input name=\"" +
                                fields.get(i).getName() +
                                "\" class='input-text'";
                        if (!fields.get(i).isVisible())
                            res += " type='hidden'";
                        res += "/>\n" +
                                "<label>" +
                                "<form:errors path=\"" +
                                fields.get(i).getName() +
                                "\"></form:errors>" +
                                "</label>\n" +
                                "</div>";
                        break;
                    case INT:
                        res += "<div class=\"col-sm-8\">\n" +
                                "<input name=\"" +
                                fields.get(i).getName() +
                                "\" class='input-text'";
                        if (!fields.get(i).isVisible())
                            res += " type='hidden'";
                        res += "/>\n" +
                                "<label>" +
                                "<form:errors path=\"" +
                                fields.get(i).getName() +
                                "\"></form:errors>" +
                                "</label>\n" +
                                "</div>";
                        break;
                    case DATE:
                        res += "<div class=\"col-sm-8\">\n" +
                                "<input type=\"date\" name=\"" +
                                fields.get(i).getName() +
                                "\" class=\"input-text\"";
                        if (!fields.get(i).isVisible())
                            res += " type='hidden'";
                        res += "/>\n" +
                                "<label>" +
                                "<form:errors path=\"" +
                                fields.get(i).getName() +
                                "\"></form:errors>" +
                                "</label>\n" +
                                "</div>";
                        break;
                    case FK:
                        List list = tableRepository.makeSql("select " + fields.get(i).getFkField() + ", " +
                                fields.get(i).getFkFieldCaption() + " From " + fields.get(i).getFkTableName());
                        res += "<div class=\"col-sm-8\">\n" +
                                "<select name='" + fields.get(i).getName() + "'>";
                        for (int k = 0; k < list.size(); k++) {
                            Object[] obj = (Object[]) list.get(k);
                            if(obj[0].toString().equals(fieldValue))
                                res += "<option value='" + obj[0] + "' selected >" + obj[1] + "</option>";
                            else
                                res += "<option value='" + obj[0] + "'>" + obj[1] + "</option>";
                        }
                        res += "</select></div>";
                        break;
                    default:
                        break;
                }
                res += "</div>";
            }
            res += "<input type=\"submit\" value=\"Добавити\" class=\"button col-sm-6\">";
            res += "</form:form>";
            return res;
        }

        public void execInsert(HttpServletRequest request){
            for(int i = 0; i < fields.size(); i++){
                fields.get(i).setValue(request.getParameter(fields.get(i).getName()));
            }
            for(int i = 0; i < onBeforeInsert.size(); i++){
                System.out.println(i);
                System.out.println(onBeforeInsert.get(i));
                if (!func(onBeforeInsert.get(i), this)){
                    return;
                }
            }
            insert();
        }

        public void insert(){
            String values = "( '";
            String res = "INSERT INTO "+ tableName + " ( ";
            for(int i = 0; i < fields.size(); i++) {
                if(!fields.get(i).isGeneratedValue()) {
                    res += fields.get(i).getName() + ", ";
                    values += fields.get(i).getValue() + "', '";
                }
            }
            res = res.substring(0, res.length() - 2);
            values = values.substring(0, values.length() - 3);
            res += " ) VALUES "+values + " ) ";
            tableRepository.makeInsert(res);
        }

        public String delete(String idTable, String id){
            String res = "<a href='javascript:deleteRecord("+idTable+","+id+")'" +
                    " class='button col-sm-3'>Видалити</a>";
            return res;
        }

        public void delete(String id){
            String res = "DELETE FROM " + tableName;
            res += " WHERE "+idFieldName + " = '" + id + "'";
            this.tableRepository.makeDelete(res);
        }


    }
}
