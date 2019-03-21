/*
 * Copyright 2012-2019 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.data.couchbase.config;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseBucket;

import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.couchbase.core.CouchbaseExceptionTranslator;

/**
 * The Factory Bean to help {@link CouchbaseBucketParser} constructing a {@link Bucket} from a given
 * {@link Cluster} reference.
 *
 * @author Simon Baslé
 */
public class CouchbaseBucketFactoryBean extends AbstractFactoryBean<Bucket> implements PersistenceExceptionTranslator {

	private final Cluster cluster;
	private final String bucketName;
	private final String bucketPassword;

	private final PersistenceExceptionTranslator exceptionTranslator = new CouchbaseExceptionTranslator();

	public CouchbaseBucketFactoryBean(Cluster cluster) {
		this(cluster, null, null);
	}

	public CouchbaseBucketFactoryBean(Cluster cluster, String bucketName) {
		this(cluster, bucketName, null);
	}

	public CouchbaseBucketFactoryBean(Cluster cluster, String bucketName, String bucketPassword) {
		this.cluster = cluster;
		this.bucketName = bucketName;
		this.bucketPassword = bucketPassword;
	}

	@Override
	public Class<?> getObjectType() {
		return CouchbaseBucket.class;
	}

	@Override
	protected Bucket createInstance() throws Exception {
		if (bucketName == null) {
			return cluster.openBucket();
		}
		else if (bucketPassword == null) {
			return cluster.openBucket(bucketName);
		}
		else {
			return cluster.openBucket(bucketName, bucketPassword);
		}
	}

	@Override
	public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
		return exceptionTranslator.translateExceptionIfPossible(ex);
	}
}
