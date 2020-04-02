package com.hebei.core.service.template;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.io.InputStream;

/**
 * @author: mapper
 * @since: 2020/4/2
 */
public class MyGridFsTemplate extends GridFsTemplate {

    private final MongoDbFactory dbFactory;

    public MyGridFsTemplate(MongoDbFactory dbFactory, MongoConverter converter) {
        super(dbFactory, converter, null);
        this.dbFactory = dbFactory;
    }

    public ObjectId store(InputStream content, String filename, String contentType, String dbName) {
        return store(content, filename, contentType, null, dbName);
    }

    public ObjectId store(InputStream content, @Nullable String filename, @Nullable String contentType,
                          @Nullable Object metadata, String dbName) {
        return store(content, filename, contentType, toDocument(metadata), dbName);
    }

    public ObjectId store(InputStream content, @Nullable String filename, @Nullable String contentType,
                          @Nullable Document metadata, String dbName) {
        Assert.notNull(content, "InputStream must not be null!");
        return getGridFs(dbName).uploadFromStream(filename, content, computeUploadOptionsFor(contentType, metadata));
    }

    public GridFSFile findOne(Query query, String dbName) {
        return find(query, dbName).first();
    }

    public GridFSFindIterable find(Query query, String dbName) {
        Assert.notNull(query, "Query must not be null!");
        Document queryObject = getMappedQuery(query.getQueryObject());
        Document sortObject = getMappedQuery(query.getSortObject());
        return getGridFs(dbName).find(queryObject).sort(sortObject);
    }

    public GridFsResource getResource(GridFSFile file,String dbName) {
        Assert.notNull(file, "GridFSFile must not be null!");
        return new GridFsResource(file, getGridFs(dbName).openDownloadStream(file.getId()));
    }

    private GridFSBucket getGridFs(String dbName) {
        MongoDatabase db = dbFactory.getDb(dbName);
        return GridFSBuckets.create(db);
    }

}
