package org.devcon.tool.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2012-01-12 19:30:09")
/** */
public final class UploadedDataMeta extends org.slim3.datastore.ModelMeta<org.devcon.tool.model.UploadedData> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<org.devcon.tool.model.UploadedData, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<org.devcon.tool.model.UploadedData, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<org.devcon.tool.model.UploadedData, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<org.devcon.tool.model.UploadedData, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<org.devcon.tool.model.UploadedData> fileName = new org.slim3.datastore.StringAttributeMeta<org.devcon.tool.model.UploadedData>(this, "fileName", "fileName");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<org.devcon.tool.model.UploadedData, java.lang.Integer> length = new org.slim3.datastore.CoreAttributeMeta<org.devcon.tool.model.UploadedData, java.lang.Integer>(this, "length", "length", int.class);

    private static final UploadedDataMeta slim3_singleton = new UploadedDataMeta();

    /**
     * @return the singleton
     */
    public static UploadedDataMeta get() {
       return slim3_singleton;
    }

    /** */
    public UploadedDataMeta() {
        super("UploadedData", org.devcon.tool.model.UploadedData.class);
    }

    @Override
    public org.devcon.tool.model.UploadedData entityToModel(com.google.appengine.api.datastore.Entity entity) {
        org.devcon.tool.model.UploadedData model = new org.devcon.tool.model.UploadedData();
        model.setKey(entity.getKey());
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        model.setFileName((java.lang.String) entity.getProperty("fileName"));
        model.setLength(longToPrimitiveInt((java.lang.Long) entity.getProperty("length")));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        org.devcon.tool.model.UploadedData m = (org.devcon.tool.model.UploadedData) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("version", m.getVersion());
        entity.setProperty("fileName", m.getFileName());
        entity.setProperty("length", m.getLength());
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        org.devcon.tool.model.UploadedData m = (org.devcon.tool.model.UploadedData) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        org.devcon.tool.model.UploadedData m = (org.devcon.tool.model.UploadedData) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        org.devcon.tool.model.UploadedData m = (org.devcon.tool.model.UploadedData) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        org.devcon.tool.model.UploadedData m = (org.devcon.tool.model.UploadedData) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
    }

    @Override
    protected void postGet(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        org.devcon.tool.model.UploadedData m = (org.devcon.tool.model.UploadedData) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        if(m.getFileName() != null){
            writer.setNextPropertyName("fileName");
            encoder0.encode(writer, m.getFileName());
        }
        writer.setNextPropertyName("length");
        encoder0.encode(writer, m.getLength());
        if(m.getFragmentListRef() != null){
            writer.setNextPropertyName("fragmentListRef");
            encoder0.encode(writer, m.getFragmentListRef());
        }
        writer.endObject();
    }

    @Override
    protected org.devcon.tool.model.UploadedData jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        org.devcon.tool.model.UploadedData m = new org.devcon.tool.model.UploadedData();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        reader = rootReader.newObjectReader("fileName");
        m.setFileName(decoder0.decode(reader, m.getFileName()));
        reader = rootReader.newObjectReader("length");
        m.setLength(decoder0.decode(reader, m.getLength()));
        reader = rootReader.newObjectReader("fragmentListRef");
        return m;
    }
}