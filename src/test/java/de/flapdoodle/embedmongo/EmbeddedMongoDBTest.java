/**
 * Copyright (C) 2011 Michael Mosmann <michael@mosmann.de>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.flapdoodle.embedmongo;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

import junit.framework.TestCase;

import de.flapdoodle.embedmongo.distribution.Version;

public class EmbeddedMongoDBTest extends TestCase {

	public void testNothing() {

	}

	public void NOtestCheck() throws IOException, InterruptedException {
		//		Distribution distribution = new Distribution(Version.V1_6_5, Platform.Linux, BitSize.B32);
		int port = 12345;
		EmbeddedMongoDB embeddedMongo = new EmbeddedMongoDB(new MongodConfig(Version.V1_6_5, port));
		MongodProcess mongod = embeddedMongo.start();
		assertNotNull("Mongod", mongod);

		Thread.sleep(1000);
		
		Mongo mongo=new Mongo("localhost", port);
		DB db = mongo.getDB("test");
		DBCollection col = db.createCollection("testCol", new BasicDBObject());
		col.save(new BasicDBObject("testDoc",new Date()));
		Thread.sleep(10000);

		mongod.stop();

		//		EmbeddedMongoDB.checkDistribution(distribution);
		//		
		//		File artifact = LocalArtifactStore.getArtifact(distribution);
		//		System.out.println("Artifact: "+artifact);
		//
		//		IExtractor extractor = Extractors.getExtractor(distribution);
		//		extractor.extract(artifact, Files.createTempFile("extract",Paths.getMongodExecutable(distribution)),Paths.getMongodExecutablePattern(distribution));
	}

}
