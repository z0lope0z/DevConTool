package org.devcon.tool.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2012-01-12 19:30:09")
/** */
public final class UploadedDataFragmentMeta extends org.slim3.datastore.ModelMeta<org.devcon.tool.model.UploadedDataFragment> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<org.devcon.tool.model.UploadedDataFragment, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<org.devcon.tool.model.UploadedDataFragment, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CoreUnindexedAttributeMeta<org.devcon.tool.model.UploadedDataFragment, byte[]> bytes = new org.slim3.datastore.CoreUnindexedAttributeMeta<org.devcon.tool.model.UploadedDataFragment, byte[]>(this, "bytes", "bytes", byte[].class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<org.devcon.tool.model.UploadedDataFragment, com.google.appengine.api.datastore.ShortBlob> bytes2 = new org.slim3.datastore.CoreAttributeMeta<org.devcon.tool.model.UploadedDataFragment, com.google.appengine.api.datastore.ShortBlob>(this, "bytes2", "bytes2", com.google.appengine.api.datastore.ShortBlob.class);

    /** */
    public final org.slim3.datastore.ModelRefAttributeMeta<org.devcon.tool.model.UploadedDataFragment, org.slim3.datastore.ModelRef<org.devcon.tool.model.UploadedData>, org.devcon.tool.model.UploadedData> uploadDataRef = new org.slim3.datastore.ModelRefAttributeMeta<org.devcon.tool.model.UploadedDataFragment, org.slim3.datastore.ModelRef<org.devcon.tool.model.UploadedData>, org.devcon.tool.model.UploadedData>(this, "uploadDataRef", "uploadDataRef", org.slim3.datastore.ModelRef.class, org.devcon.tool.model.UploadedData.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<org.devcon.tool.model.UploadedDataFragment, java.lang.Integer> index = new org.slim3.datastore.CoreAttributeMeta<org.devcon.tool.model.UploadedDataFragment, java.lang.Integer>(this, "index", "index", int.class);

    private static final UploadedDataFragmentMeta slim3_singleton = new UploadedDataFragmentMeta();

    /**
     * @return the singleton
     */
    public static UploadedDataFragmentMeta get() {
       return slim3_singleton;
    }

    /** */
    public UploadedDataFragmentMeta() {
        super("UploadedDataFragment", org.devcon.tool.model.UploadedDataFragment.class);
    }

    @Override
    public org.devcon.tool.model.UploadedDataFragment entityToModel(com.google.appengine.api.datastore.Entity entity) {
        org.devcon.tool.model.UploadedDataFragment model = new org.devcon.tool.model.UploadedDataFragment();
        model.setKey(entity.getKey());
        model.setBytes(blobToBytes((com.google.appengine.api.datastore.Blob) entity.getProperty("bytes")));
        model.setBytes2((com.google.appengine.api.datastore.ShortBlob) entity.getProperty("bytes2"));
        if (model.getUploadDataRef() == null) {
            throw new NullPointerException("The property(uploadDataRef) is null.");
        }
        model.getUploadDataRef().setKey((com.google.appengine.api.datastore.Key) entity.getProperty("uploadDataRef"));
        model.setIndex(longToPrimitiveInt((java.lang.Long) entity.getProperty("index")));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        org.devcon.tool.model.UploadedDataFragment m = (org.devcon.tool.model.UploadedDataFragment) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setUnindexedProperty("bytes", bytesToBlob(m.getBytes()));
        entity.setProperty("bytes2", m.getBytes2());
        if (m.getUploadDataRef() == null) {
            throw new NullPointerException("The property(uploadDataRef) must not be null.");
        }
        entity.setProperty("uploadDataRef", m.getUploadDataRef().getKey());
        entity.setProperty("index", m.getIndex());
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        org.devcon.tool.model.UploadedDataFragment m = (org.devcon.tool.model.UploadedDataFragment) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        org.devcon.tool.model.UploadedDataFragment m = (org.devcon.tool.model.UploadedDataFragment) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        throw new IllegalStateException("The version property of the model(org.devcon.tool.model.UploadedDataFragment) is not defined.");
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
        org.devcon.tool.model.UploadedDataFragment m = (org.devcon.tool.model.UploadedDataFragment) model;
        if (m.getUploadDataRef() == null) {
            throw new NullPointerException("The property(uploadDataRef) must not be null.");
        }
        m.getUploadDataRef().assignKeyIfNecessary(ds);
    }

    @Override
    protected void incrementVersion(Object model) {
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
        org.devcon.tool.model.UploadedDataFragment m = (org.devcon.tool.model.UploadedDataFragment) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getBytes() != null){
            writer.setNextPropertyName("bytes");
            encoder0.encode(writer, new com.google.appengine.api.datastore.ShortBlob(m.getBytes()));
        }
        if(m.getBytes2() != null){
            writer.setNextPropertyName("bytes2");
            encoder0.encode(writer, m.getBytes2());
        }
        if(m.getUploadDataRef() != null && m.getUploadDataRef().getKey() != null){
            writer.setNextPropertyName("uploadDataRef");
            encoder0.encode(writer, m.getUploadDataRef(), maxDepth, currentDepth);
        }
        writer.setNextPropertyName("index");
        encoder0.encode(writer, m.getIndex());
        writer.endObject();
    }

    @Override
    protected org.devcon.tool.model.UploadedDataFragment jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        org.devcon.tool.model.UploadedDataFragment m = new org.devcon.tool.model.UploadedDataFragment();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("bytes");
        if(m.getBytes() != null){
            m.setBytes(decoder0.decode(reader, new com.google.appengine.api.datastore.ShortBlob(m.getBytes())).getBytes());
        } else{
            com.google.appengine.api.datastore.ShortBlob v = decoder0.decode(reader, (com.google.appengine.api.datastore.ShortBlob)null);
            if(v != null){
                m.setBytes(v.getBytes());
            } else{
                m.setBytes(null);
            }
        }
        reader = rootReader.newObjectReader("bytes2");
        m.setBytes2(decoder0.decode(reader, m.getBytes2()));
        reader = rootReader.newObjectReader("uploadDataRef");
        decoder0.decode(reader, m.getUploadDataRef(), maxDepth, currentDepth);
        reader = rootReader.newObjectReader("index");
        m.setIndex(decoder0.decode(reader, m.getIndex()));
        return m;
    }
}