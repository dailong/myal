package com.my.hudi;

import org.apache.hudi.QuickstartUtils;
import org.apache.hudi.client.HoodieJavaWriteClient;
import org.apache.hudi.client.SparkRDDWriteClient;
import org.apache.hudi.client.common.HoodieSparkEngineContext;
import org.apache.hudi.common.engine.HoodieEngineContext;
import org.apache.hudi.common.model.HoodieAvroPayload;
import org.apache.hudi.common.model.HoodieRecord;
import org.apache.hudi.common.model.HoodieTableType;
import org.apache.hudi.common.table.HoodieTableMetaClient;
import org.apache.hudi.config.HoodieCompactionConfig;
import org.apache.hudi.config.HoodieIndexConfig;
import org.apache.hudi.config.HoodieWriteConfig;
import org.apache.hudi.examples.common.HoodieExampleDataGenerator;
import org.apache.hudi.index.HoodieIndex;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dailong
 * @create 2021-03-14 16:45
 **/

public class HudiClient {
    private static final Logger LOG = LogManager.getLogger(HudiClient.class);
    private static String tableType = HoodieTableType.COPY_ON_WRITE.name();
    public static String TRIP_EXAMPLE_SCHEMA = "{\"type\": \"record\",\"name\": \"triprec\",\"fields\": [ "
            + "{\"name\": \"ts\",\"type\": \"long\"},{\"name\": \"uuid\", \"type\": \"string\"},"
            + "{\"name\": \"rider\", \"type\": \"string\"},{\"name\": \"driver\", \"type\": \"string\"},"
            + "{\"name\": \"begin_lat\", \"type\": \"double\"},{\"name\": \"begin_lon\", \"type\": \"double\"},"
            + "{\"name\": \"end_lat\", \"type\": \"double\"},{\"name\": \"end_lon\", \"type\": \"double\"},"
            + "{\"name\":\"fare\",\"type\": \"double\"}]}";
    public static void main(String[] args) throws IOException {


        String basePath = "file:///tmp/hudi/";
        String tablePath = basePath + "demo";
        SparkConf sparkConf = new SparkConf();
        sparkConf.setMaster("local[2]");
        sparkConf.setAppName("hudidemo");
        sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);
        HoodieEngineContext context = new HoodieSparkEngineContext(javaSparkContext);
        HoodieTableMetaClient.initTableType(javaSparkContext.hadoopConfiguration(), tablePath, HoodieTableType.valueOf(tableType),
                "demo", HoodieAvroPayload.class.getName());

        HoodieWriteConfig cfg = HoodieWriteConfig.newBuilder().withPath(tablePath)
                .withSchema(TRIP_EXAMPLE_SCHEMA).withParallelism(2, 2)
                .withDeleteParallelism(2).forTable("demo")
                .withIndexConfig(HoodieIndexConfig.newBuilder().withIndexType(HoodieIndex.IndexType.BLOOM).build())
                .withCompactionConfig(HoodieCompactionConfig.newBuilder().archiveCommitsWith(20, 30).build()).build();
        SparkRDDWriteClient<HoodieAvroPayload> client = new SparkRDDWriteClient<>(new HoodieSparkEngineContext(javaSparkContext), cfg);

        String newCommitTime = client.startCommit();
        LOG.info("Starting commit " + newCommitTime);

        HoodieExampleDataGenerator<HoodieAvroPayload> dataGen = new HoodieExampleDataGenerator<>();
        List<HoodieRecord<HoodieAvroPayload>> records = dataGen.generateInserts(newCommitTime, 10);
        List<HoodieRecord<HoodieAvroPayload>> recordsSoFar = new ArrayList<>(records);
        JavaRDD<HoodieRecord<HoodieAvroPayload>> writeRecords = javaSparkContext.parallelize(records, 1);
        client.upsert(writeRecords, newCommitTime);


    }
}
