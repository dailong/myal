package com.my.hudi;

import org.apache.hudi.DataSourceWriteOptions;
import org.apache.hudi.QuickstartUtils;
import org.apache.hudi.avro.HoodieAvroUtils;
import org.apache.hudi.common.model.HoodieAvroPayload;
import org.apache.hudi.common.model.HoodieRecord;
import org.apache.hudi.common.util.Option;
import org.apache.hudi.config.HoodieWriteConfig;
import org.apache.hudi.timeline.service.TimelineService;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.apache.hudi.config.HoodieWriteConfig.TABLE_NAME;

//import static org.apache.hudi.config.HoodieWriteConfig.TABLE_NAME;

/**
 * @author dailong
 * @create 2021-03-08 20:46
 **/

public class HudiDemo {
    public static void main(String[] args) throws Exception {
//        TimelineService.main(null);
//        TimeUnit.SECONDS.sleep(10000);


        String basePath = "file:///tmp/hudi/";
        SparkConf sparkConf = new SparkConf();
        sparkConf.setMaster("local[2]");
        sparkConf.setAppName("hudidemo");
        sparkConf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);
        SparkSession session = SparkSession.builder()
                .master("local[2]")
                .config(sparkConf)
                .config("spark.driver.memory",(512 * 1014 * 1024)+"")
                .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
                .getOrCreate();

        QuickstartUtils.DataGenerator dataGenerator = new QuickstartUtils.DataGenerator();
        List<HoodieRecord> hoodieRecords = dataGenerator.generateInserts(10);
        List<String> dataList = QuickstartUtils.convertToStringList(hoodieRecords);

        System.out.println(hoodieRecords.size());


//        Dataset<Row> dataset = session.read().json(javaSparkContext.parallelize(dataList, 2));



        Dataset<Row> dataset = session.read().json("file:///D:/tmp/people.json");
        dataset.printSchema();
        dataset.show();
        dataset.write().format("hudi").
                option("PRECOMBINE_FIELD_OPT_KEY", "ts").
                option("RECORDKEY_FIELD_OPT_KEY", "uuid").
                option("PARTITIONPATH_FIELD_OPT_KEY", "partitionpath").
                option(DataSourceWriteOptions.TABLE_TYPE_OPT_KEY(), DataSourceWriteOptions.MOR_TABLE_TYPE_OPT_VAL()).
                option(TABLE_NAME, "people").
//                mode(SaveMode.Overwrite).
                mode(SaveMode.Append).
//                save(basePath + "hudi_trips_cow");
                save(basePath + "people");
////        TimeUnit.SECONDS.sleep(10000);
//
//
//
//
//        Dataset<Row> ds = session.read().
//                format("hudi").
//                load("file:///D:/tmp/hudi/*/*");
//        ds.createOrReplaceTempView("hudi_trips_snapshot");
//        session.sql("select * from hudi_trips_snapshot").show(false);

//        session.sql("select _hoodie_commit_time, _hoodie_record_key, _hoodie_partition_path, rider, driver, fare from  hudi_trips_snapshot").show()


    }
}
