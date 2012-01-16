package org.devcon.tool.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2012-01-16 21:45:38")
/** */
public final class MemberMeta extends org.slim3.datastore.ModelMeta<org.devcon.tool.model.Member> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<org.devcon.tool.model.Member, com.google.appengine.api.datastore.Email> email = new org.slim3.datastore.CoreAttributeMeta<org.devcon.tool.model.Member, com.google.appengine.api.datastore.Email>(this, "email", "email", com.google.appengine.api.datastore.Email.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<org.devcon.tool.model.Member, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<org.devcon.tool.model.Member, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<org.devcon.tool.model.Member> name = new org.slim3.datastore.StringAttributeMeta<org.devcon.tool.model.Member>(this, "name", "name");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<org.devcon.tool.model.Member, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<org.devcon.tool.model.Member, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final MemberMeta slim3_singleton = new MemberMeta();

    /**
     * @return the singleton
     */
    public static MemberMeta get() {
       return slim3_singleton;
    }

    /** */
    public MemberMeta() {
        super("Member", org.devcon.tool.model.Member.class);
    }

    @Override
    public org.devcon.tool.model.Member entityToModel(com.google.appengine.api.datastore.Entity entity) {
        org.devcon.tool.model.Member model = new org.devcon.tool.model.Member();
        model.setEmail((com.google.appengine.api.datastore.Email) entity.getProperty("email"));
        model.setKey(entity.getKey());
        model.setName((java.lang.String) entity.getProperty("name"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        org.devcon.tool.model.Member m = (org.devcon.tool.model.Member) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("email", m.getEmail());
        entity.setProperty("name", m.getName());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        org.devcon.tool.model.Member m = (org.devcon.tool.model.Member) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        org.devcon.tool.model.Member m = (org.devcon.tool.model.Member) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        org.devcon.tool.model.Member m = (org.devcon.tool.model.Member) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        org.devcon.tool.model.Member m = (org.devcon.tool.model.Member) model;
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
        org.devcon.tool.model.Member m = (org.devcon.tool.model.Member) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getEmail() != null){
            writer.setNextPropertyName("email");
            encoder0.encode(writer, m.getEmail());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getName() != null){
            writer.setNextPropertyName("name");
            encoder0.encode(writer, m.getName());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected org.devcon.tool.model.Member jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        org.devcon.tool.model.Member m = new org.devcon.tool.model.Member();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("email");
        m.setEmail(decoder0.decode(reader, m.getEmail()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("name");
        m.setName(decoder0.decode(reader, m.getName()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}