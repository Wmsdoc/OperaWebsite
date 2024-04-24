-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: opera
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `activity_id` bigint NOT NULL COMMENT '活动编号',
  `activity_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin NOT NULL COMMENT '活动名称',
  `activity_info` varchar(200) COLLATE utf8mb4_0900_bin DEFAULT NULL COMMENT '活动简介',
  `activity_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin DEFAULT NULL COMMENT '活动地址',
  `activity_num` int DEFAULT NULL COMMENT '活动人数',
  `start_time` datetime DEFAULT NULL COMMENT '活动开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '活动结束时间',
  `is_delete` int DEFAULT '0' COMMENT '是否删除',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` bigint DEFAULT NULL COMMENT '创建人',
  `updated_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin COMMENT='活动表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,'专家讲座','测试活动','厦门市集美区',12,'2023-12-19 10:19:00','2023-12-29 10:19:00',0,'2023-12-05 10:19:28','2024-04-20 00:29:49',1,1),(2,'戏曲论坛会','哈哈哈','福建',15,'2023-12-05 15:21:00','2023-12-11 15:21:00',0,'2023-12-05 15:21:14','2024-04-20 00:29:41',2,1),(1735630192908099586,'团建交流','aaa','集美区软件园',10,'2023-12-28 19:57:00','2023-12-31 19:57:00',0,'2023-12-15 19:57:39','2024-04-20 00:29:26',1,1),(1735630478049468417,'厦门市戏曲交流会','bbb','福建省厦门市',3,'2023-12-26 19:58:00','2023-12-28 19:58:00',0,'2023-12-15 19:58:47','2024-04-20 00:29:12',1,1),(1735635785551118337,'诚毅学院戏曲论坛','ccc','诚毅学院',3,'2023-12-26 20:19:00','2023-12-29 20:19:00',0,'2023-12-15 20:19:52','2024-04-20 00:28:55',1,1),(1735934327437504514,'ddd','d','d',1,'2023-12-27 16:06:00','2023-12-29 16:06:00',1,'2023-12-16 16:06:10','2023-12-16 16:07:30',1,NULL);
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collection_audio`
--

DROP TABLE IF EXISTS `collection_audio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `collection_audio` (
  `collection_id` bigint NOT NULL COMMENT '收藏音频编号',
  `playgoer_id` bigint DEFAULT NULL COMMENT '戏迷编号',
  `audio_id` bigint DEFAULT NULL COMMENT '戏曲音频编号',
  `is_delete` int DEFAULT '0' COMMENT '是否删除',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` bigint DEFAULT NULL COMMENT '创建人',
  `updated_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`collection_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin COMMENT='戏迷戏曲音频收藏关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collection_audio`
--

LOCK TABLES `collection_audio` WRITE;
/*!40000 ALTER TABLE `collection_audio` DISABLE KEYS */;
INSERT INTO `collection_audio` VALUES (1,1,1,1,'2023-11-28 15:14:58','2023-11-28 16:23:29',1,NULL),(1729418206877093890,1,1,1,'2023-11-28 16:33:26','2023-12-07 10:08:29',NULL,NULL),(1732582876010000386,1,1731504972526260225,1,'2023-12-07 10:08:42','2023-12-07 10:36:23',NULL,NULL),(1732589896578695170,1,1731504972526260225,0,'2023-12-07 10:36:35','2023-12-07 10:36:35',NULL,NULL),(1732672821735247873,1,1,0,'2023-12-07 16:06:06','2023-12-07 16:06:06',NULL,NULL),(1732672836780216322,1,6,0,'2023-12-07 16:06:10','2023-12-07 16:06:10',NULL,NULL),(1732672850592059394,1,5,0,'2023-12-07 16:06:13','2023-12-07 16:06:13',NULL,NULL),(1735208510623784962,1,4,0,'2023-12-14 16:02:02','2023-12-14 16:02:02',NULL,NULL),(1737075325952221186,1,1737075161812328450,1,'2023-12-19 19:40:05','2024-04-12 17:05:47',NULL,NULL),(1778711104031141890,1,1737075161812328450,0,'2024-04-12 17:05:48','2024-04-12 17:05:48',NULL,NULL),(1781368484785430530,2,1781366076252495873,0,'2024-04-20 01:05:17','2024-04-20 01:05:17',NULL,NULL),(1781368526304845826,2,1781342166370918401,0,'2024-04-20 01:05:27','2024-04-20 01:05:27',NULL,NULL);
/*!40000 ALTER TABLE `collection_audio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collection_video`
--

DROP TABLE IF EXISTS `collection_video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `collection_video` (
  `collection_id` bigint NOT NULL COMMENT '收藏视频编号',
  `playgoer_id` bigint DEFAULT NULL COMMENT '戏迷编号',
  `video_id` bigint DEFAULT NULL COMMENT '戏曲视频编号',
  `is_delete` int DEFAULT '0' COMMENT '是否删除',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` bigint DEFAULT NULL COMMENT '创建人',
  `updated_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`collection_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin COMMENT='戏迷戏曲视频收藏关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collection_video`
--

LOCK TABLES `collection_video` WRITE;
/*!40000 ALTER TABLE `collection_video` DISABLE KEYS */;
INSERT INTO `collection_video` VALUES (1,1,1,1,'2023-11-28 15:22:37','2023-11-28 16:41:58',1,NULL),(1729420406399463425,1,1,1,'2023-11-28 16:42:10','2023-12-07 12:24:11',NULL,NULL),(1732617010707193857,1,1,0,'2023-12-07 12:24:20','2023-12-07 12:24:20',NULL,NULL),(1737012999811313666,1,2,0,'2023-12-19 15:32:25','2023-12-19 15:32:25',NULL,NULL),(1781368279793016834,1,1781350419028942849,0,'2024-04-20 01:04:28','2024-04-20 01:04:28',NULL,NULL),(1781368309539020801,1,1781329259293745154,1,'2024-04-20 01:04:35','2024-04-20 01:16:48',NULL,NULL),(1781368345178021890,1,1781328742400303106,0,'2024-04-20 01:04:44','2024-04-20 01:04:44',NULL,NULL),(1781368406263865345,2,1781329028338589697,0,'2024-04-20 01:04:58','2024-04-20 01:04:58',NULL,NULL),(1781368419916324865,2,1781328147312451586,0,'2024-04-20 01:05:02','2024-04-20 01:05:02',NULL,NULL),(1781371384995651586,1,1781329259293745154,1,'2024-04-20 01:16:49','2024-04-20 01:24:26',NULL,NULL),(1781373305601007618,1,1781329259293745154,0,'2024-04-20 01:24:27','2024-04-20 01:24:27',NULL,NULL);
/*!40000 ALTER TABLE `collection_video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opera_audio`
--

DROP TABLE IF EXISTS `opera_audio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `opera_audio` (
  `audio_id` bigint NOT NULL COMMENT '戏曲编号',
  `audio_info` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin DEFAULT NULL COMMENT '戏曲简介',
  `type_id` bigint DEFAULT NULL COMMENT '类型编号',
  `download_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `download_num` int DEFAULT '0' COMMENT '戏曲音频下载量',
  `filename` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin DEFAULT NULL COMMENT '视频文件名称',
  `is_examine` int NOT NULL DEFAULT '0' COMMENT '是否审核',
  `is_delete` int DEFAULT '0' COMMENT '是否删除',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` bigint DEFAULT NULL COMMENT '创建人',
  `updated_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`audio_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin COMMENT='戏曲音频表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opera_audio`
--

LOCK TABLES `opera_audio` WRITE;
/*!40000 ALTER TABLE `opera_audio` DISABLE KEYS */;
INSERT INTO `opera_audio` VALUES (1,'测试',1,NULL,10,'aaa',1,1,'2023-11-20 23:07:22','2024-04-19 16:34:58',1,NULL),(2,'测试',1,NULL,0,'bbb',1,1,'2023-11-20 23:07:22','2024-04-19 16:34:53',1,1),(3,'测试',1,NULL,0,'ccc',1,1,'2023-11-20 23:07:22','2024-04-19 16:34:55',1,1),(4,'yonfjdfo',4,NULL,0,'ddd',1,1,'2023-11-20 23:07:22','2024-04-06 15:30:10',1,1),(5,'测试',1,NULL,0,'eee',1,1,'2022-11-20 23:07:22','2023-12-14 10:26:45',1,NULL),(6,'测试',1,NULL,0,'fff',0,1,'2022-11-20 23:07:22','2023-12-12 10:07:20',1,NULL),(1731504972526260225,'这是文件上传测试，音频，管理员修改',4,'https://opera-all.oss-cn-beijing.aliyuncs.com/audio/8067276e-e497-4888-8005-9828d999c325.flac',33,'枫叶城 - 单循（Bunnyi_11）',1,1,'2023-12-04 10:45:29','2024-04-19 16:34:51',1,1),(1736740944494616578,'啊啊啊',4,'https://opera-all.oss-cn-beijing.aliyuncs.com/audio/91d11f3a-2b49-42d2-ad9a-414d075980f6.mp4',1,'乱码',0,1,'2023-12-18 21:31:22','2024-04-19 16:34:44',1,1),(1737075161812328450,'杨门女将 - 宗保诞辰心欢畅\r\n产于创作的作家：刁丽;\r\n\r\n唱片集：京剧之星 刁丽专辑',1,'https://opera-all.oss-cn-beijing.aliyuncs.com/audio/46200b2f-8c2f-452d-a4b5-0838a5832411.mp3',3,'杨门女将 - 宗保诞辰心欢畅',1,1,'2023-12-19 19:39:26','2024-04-19 16:34:46',1,1),(1747566177325969409,'戏曲',2,'https://opera-all.oss-cn-beijing.aliyuncs.com/audio/cd89f1c9-6cf8-4765-a6a2-4f622837a280.mp3',0,'杨门女将 - 宗保诞辰心欢畅',1,1,'2024-01-17 18:26:59','2024-04-19 16:34:48',1,1),(1781339469970317314,'杨门女将',1,'https://opera-all.oss-cn-beijing.aliyuncs.com/audio/fb53e399-e278-4f9e-83ef-3d7a7ff018d3.mp3',0,'杨门女将 - 宗保诞辰心欢畅',1,0,'2024-04-19 23:09:59','2024-04-19 23:16:20',1,1),(1781339692742385666,'女驸马',3,'https://opera-all.oss-cn-beijing.aliyuncs.com/audio/9a55b372-f421-4d73-b2af-8be8075de751.mp3',0,'黄梅戏女驸马 - 韩再芬',1,0,'2024-04-19 23:10:53','2024-04-19 23:16:19',1,1),(1781340463856787458,'孟姜女',3,'https://opera-all.oss-cn-beijing.aliyuncs.com/audio/04f3e285-094a-4a7e-9a7f-a9dc20bef9b5.mp3',0,'黄梅戏《孟姜女》十二月调 - 吴琼[戏曲]',1,0,'2024-04-19 23:13:56','2024-04-19 23:16:19',1,1),(1781340841520308225,'黄梅戏龙女选段',3,'https://opera-all.oss-cn-beijing.aliyuncs.com/audio/053088fd-c312-4391-846f-6c342cfba0d6.mp3',0,'韩再芬-一月思念KTV（黄梅戏《龙女》选段） - 琅嬛书童',1,0,'2024-04-19 23:15:27','2024-04-19 23:16:18',1,1),(1781341192885542913,'【越剧】双烈记 戎马生涯 - 玉山公子',2,'https://opera-all.oss-cn-beijing.aliyuncs.com/audio/1b0e7454-cb3c-4f10-8a1f-313abe66c076.mp3',0,'【越剧】双烈记 戎马生涯 - 玉山公子',1,0,'2024-04-19 23:16:50','2024-04-19 23:17:20',1,1),(1781341281163059202,'【越剧】红楼梦 + 陆游与唐琬 + 楼台会 + 十八相送 + 断桥 - 戏曲',2,'https://opera-all.oss-cn-beijing.aliyuncs.com/audio/e4b528b2-8cfb-4389-bcf7-4404b3c6d701.mp3',0,'【越剧】红楼梦 + 陆游与唐琬 + 楼台会 + 十八相送 + 断桥 - 戏曲',1,0,'2024-04-19 23:17:11','2024-04-19 23:17:20',1,1),(1781342166370918401,'天仙配',3,'https://opera-all.oss-cn-beijing.aliyuncs.com/audio/5a52ccac-7cc4-4490-a6e6-d3630f4963cc.mp3',0,'天仙配(夫妻双双把家还) - 阎维文、张也',1,0,'2024-04-19 23:20:42','2024-04-19 23:21:05',1,1),(1781366076252495873,'上传',2,'https://opera-all.oss-cn-beijing.aliyuncs.com/audio/6450b8f3-dc96-4791-a6b3-1a68f53bf752.mp3',0,'【黄梅戏】对花 - 卓依婷',1,0,'2024-04-20 00:55:43','2024-04-20 00:56:53',2,1),(1781371898839834626,'上传',2,'https://opera-all.oss-cn-beijing.aliyuncs.com/audio/baed7fd4-eb51-4b89-851a-6984d7c28f35.mp3',0,'十八相送 - 越剧',0,1,'2024-04-20 01:18:51','2024-04-20 01:21:00',2,NULL),(1781375337015681025,'上传',2,'https://opera-all.oss-cn-beijing.aliyuncs.com/audio/e5ea0a60-c5eb-43cd-b414-ef6894546589.mp3',0,'十八相送 - 越剧',1,0,'2024-04-20 01:32:31','2024-04-20 01:34:21',2,1);
/*!40000 ALTER TABLE `opera_audio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opera_audio_comment`
--

DROP TABLE IF EXISTS `opera_audio_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `opera_audio_comment` (
  `comment_id` bigint NOT NULL COMMENT '戏曲音频评论编号',
  `audio_id` bigint NOT NULL COMMENT '音频编号',
  `playgoer_id` bigint NOT NULL COMMENT '戏迷编号',
  `comment_info` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin NOT NULL COMMENT '评论内容',
  `is_delete` int DEFAULT '0' COMMENT '是否删除',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` bigint DEFAULT NULL COMMENT '创建人',
  `updated_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin COMMENT='戏曲音频评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opera_audio_comment`
--

LOCK TABLES `opera_audio_comment` WRITE;
/*!40000 ALTER TABLE `opera_audio_comment` DISABLE KEYS */;
INSERT INTO `opera_audio_comment` VALUES (1,1,1,'第一第一！！！',1,'2023-11-23 15:30:37','2024-04-19 16:34:24',1,NULL),(2,1,2,'第二',1,'2023-11-23 15:46:44','2024-04-19 16:34:38',2,NULL),(1730055296627265538,1,1,'aaaa',1,'2023-11-30 10:45:00','2024-04-19 16:34:34',1,NULL),(1730055746382483457,1,1,'igfuyui',1,'2023-11-30 10:46:47','2023-12-13 10:50:58',1,1),(1730056563135107073,1,1,'ccc',1,'2023-11-30 10:50:02','2024-04-19 16:34:32',1,NULL),(1730057008851210242,1,1,'ddd',1,'2023-11-30 10:51:48','2023-12-13 10:51:31',1,NULL),(1730548470747516930,1,2,'2222',1,'2023-12-01 19:24:42','2024-04-19 16:34:26',2,1),(1730550361841750018,1,1,'冲冲',1,'2023-12-01 19:32:12','2023-12-08 14:48:44',1,NULL),(1731587638080655362,1731504972526260225,1,'这是第一条评论哟',1,'2023-12-04 16:13:58','2024-04-19 16:34:29',1,NULL),(1735208491212546050,4,1,'aaa',0,'2023-12-14 16:01:57','2023-12-14 16:01:57',1,NULL),(1737012743648391170,1731504972526260225,1,'aaa',1,'2023-12-19 15:31:24','2024-04-19 16:34:21',1,NULL),(1773534113296396290,1737075161812328450,1,'张三到此一游',1,'2024-03-29 10:14:17','2024-04-19 16:34:19',1,NULL),(1776517028849733633,1737075161812328450,1,'评论发布测试',1,'2024-04-06 15:47:20','2024-04-19 16:34:16',1,NULL),(1781368639974678530,1781341192885542913,2,'好听好听！！！',0,'2024-04-20 01:05:54','2024-04-20 01:05:54',2,NULL);
/*!40000 ALTER TABLE `opera_audio_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opera_type`
--

DROP TABLE IF EXISTS `opera_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `opera_type` (
  `type_id` bigint NOT NULL COMMENT '类型编号',
  `type_name` varchar(20) COLLATE utf8mb4_0900_bin NOT NULL COMMENT '类型名称',
  `is_delete` int DEFAULT '0' COMMENT '是否删除 （默认0 未删除 1 已删除）',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` bigint DEFAULT NULL COMMENT '创建人',
  `updated_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin COMMENT='戏曲类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opera_type`
--

LOCK TABLES `opera_type` WRITE;
/*!40000 ALTER TABLE `opera_type` DISABLE KEYS */;
INSERT INTO `opera_type` VALUES (1,'京剧',0,'2023-11-20 10:18:30','2023-11-20 10:18:30',NULL,NULL),(2,'越剧',0,'2023-11-20 10:18:39','2023-11-20 10:18:39',NULL,NULL),(3,'黄梅戏',0,'2023-11-20 10:18:53','2023-11-20 10:18:53',NULL,NULL),(4,'晋剧',0,'2023-11-20 10:18:59','2023-11-20 10:21:29',NULL,NULL),(5,'豫剧',1,'2023-11-20 10:19:13','2023-12-14 16:04:05',NULL,NULL),(6,'昆曲',0,'2023-11-20 10:19:16','2023-11-20 10:19:16',NULL,NULL),(7,'粤剧',0,'2023-11-20 10:19:27','2023-11-20 10:19:27',NULL,NULL),(8,'秦腔',0,'2023-11-20 10:19:37','2023-11-20 10:19:37',NULL,NULL),(9,'川剧',0,'2023-11-20 10:19:48','2023-11-20 10:19:48',NULL,NULL),(10,'梨园戏',0,'2023-11-20 10:19:55','2023-11-20 10:19:55',NULL,NULL),(11,'花鼓戏',0,'2023-11-20 10:20:06','2023-11-20 10:20:06',NULL,NULL),(12,'皮影',1,'2023-11-20 10:20:15','2023-12-13 15:17:43',NULL,NULL);
/*!40000 ALTER TABLE `opera_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opera_video`
--

DROP TABLE IF EXISTS `opera_video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `opera_video` (
  `video_id` bigint NOT NULL COMMENT '戏曲编号',
  `video_info` varchar(200) COLLATE utf8mb4_0900_bin DEFAULT NULL COMMENT '戏曲简介',
  `type_id` bigint DEFAULT NULL COMMENT '类型编号',
  `download_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin DEFAULT NULL,
  `download_num` int DEFAULT '0' COMMENT '戏曲视频下载量',
  `filename` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin DEFAULT NULL COMMENT '视频文件名称',
  `is_examine` int NOT NULL DEFAULT '0' COMMENT '是否审核',
  `is_delete` int DEFAULT '0' COMMENT '是否删除',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` bigint DEFAULT NULL COMMENT '创建人',
  `updated_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`video_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin COMMENT='戏曲视频表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opera_video`
--

LOCK TABLES `opera_video` WRITE;
/*!40000 ALTER TABLE `opera_video` DISABLE KEYS */;
INSERT INTO `opera_video` VALUES (1,'管理员，后台',3,NULL,0,'aaaa',1,1,'2023-11-23 10:03:35','2024-04-19 16:33:58',1,1),(2,'aaaaodfhius',3,NULL,0,'四年冬季',0,1,'2023-12-12 23:12:48','2024-04-19 16:34:02',1,1),(3,'阿达',3,NULL,0,'阿萨阿达',1,1,'2023-12-12 23:13:03','2023-12-14 10:49:44',1,NULL),(1737013516335656962,'shangchuan',4,'https://opera-all.oss-cn-beijing.aliyuncs.com/video/5c0c2a56-75af-4c27-8719-23d7dd964042.mp4',0,'shangchuan',1,1,'2023-12-19 15:34:29','2024-04-19 16:34:00',1,1),(1781230151686070273,'将御马盗去，并留下黄三太姓名。 《盗御马》《连环套》的剧情简介 一名《坐寨盗马》，又名《盗御马》。清朝时，窦尔墩被黄三太镖伤后，愤至口外连环套聚义。十数年后闻梁九公出口外行围，携有清帝赐御马，忆及前仇，乃下山暗入梁营，将御马盗去，并留下黄三太姓名。梁九公失落御马，见柬留黄三太之名，知黄已死，乃调其子记名总兵黄天霸问罪，彭朋与黄三太有旧情，暗中屁护，限令访拿。',1,'https://opera-all.oss-cn-beijing.aliyuncs.com/video/2cc5aa3f-fecc-42ea-a7fc-0e08628f07a2.flv',0,'004062.京剧《坐寨盗马》裘盛戎',1,1,'2024-04-19 15:55:36','2024-04-19 16:34:04',1,1),(1781272352612851713,'062.川剧小品《射雕》',9,'https://opera-all.oss-cn-beijing.aliyuncs.com/video/78057c8c-79d1-49b4-9a43-6f00a44cffd9.mp4',0,'078.川剧《阖宫欢庆 》',1,0,'2024-04-19 18:43:17','2024-04-19 22:24:10',1,1),(1781272690812166145,'083.川剧《思凡 》',9,'https://opera-all.oss-cn-beijing.aliyuncs.com/video/19296479-9b35-477b-8a3e-548838609e72.mp4',0,'083.川剧《思凡 》',1,0,'2024-04-19 18:44:38','2024-04-19 22:24:09',1,1),(1781328147312451586,'080.川剧《酒楼晒衣 》',9,'https://opera-all.oss-cn-beijing.aliyuncs.com/video/8dd66f7c-2afc-4274-bc12-5aec6003729f.mp4',0,'080.川剧《酒楼晒衣 》',1,0,'2024-04-19 22:25:00','2024-04-19 22:26:28',1,1),(1781328742400303106,'092.川剧《芙蓉花仙》上 ',9,'https://opera-all.oss-cn-beijing.aliyuncs.com/video/97457097-5eb9-4e71-86af-ad561aef2c4d.mp4',0,'092.川剧《芙蓉花仙》上 ',1,0,'2024-04-19 22:27:22','2024-04-19 22:29:46',1,1),(1781329028338589697,'093.川剧《芙蓉花仙》下',9,'https://opera-all.oss-cn-beijing.aliyuncs.com/video/80308a3d-4b9d-4340-b667-7a54e4268286.mp4',0,'093.川剧《芙蓉花仙》下',1,0,'2024-04-19 22:28:30','2024-04-19 22:29:46',1,1),(1781329259293745154,'082.川剧《拿虎 》',9,'https://opera-all.oss-cn-beijing.aliyuncs.com/video/67cc72c9-411a-47ee-a576-9d584ef21582.mp4',0,'082.川剧《拿虎 》',1,0,'2024-04-19 22:29:25','2024-04-19 22:29:47',1,1),(1781350419028942849,'道仙缘奇谈，物是人非，疏却千丝怨愁。\r\n歌巾帼英姿，再添新话，重入万家灯火。\r\nCV：\r\n云堇念白&唱段——杨扬',1,'https://opera-all.oss-cn-beijing.aliyuncs.com/video/16ff21dc-9448-48bb-a346-dbe48c8bf415.mp4',0,'神女劈观',1,0,'2024-04-19 23:53:30','2024-04-19 23:56:37',1,1);
/*!40000 ALTER TABLE `opera_video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opera_video_comment`
--

DROP TABLE IF EXISTS `opera_video_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `opera_video_comment` (
  `comment_id` bigint NOT NULL COMMENT '戏曲评论编号',
  `video_id` bigint NOT NULL COMMENT '视频编号',
  `playgoer_id` bigint NOT NULL COMMENT '戏迷编号',
  `comment_info` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin NOT NULL COMMENT '评论内容',
  `is_delete` int DEFAULT '0' COMMENT '是否删除',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` bigint DEFAULT NULL COMMENT '创建人',
  `updated_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin COMMENT='戏曲视频评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opera_video_comment`
--

LOCK TABLES `opera_video_comment` WRITE;
/*!40000 ALTER TABLE `opera_video_comment` DISABLE KEYS */;
INSERT INTO `opera_video_comment` VALUES (1,1,1,'占座占座',0,'2023-11-23 15:45:10','2023-11-30 09:07:26',1,NULL),(1730059103461490690,1,1,'哈哈哈',0,'2023-11-30 11:00:07','2023-11-30 11:00:07',1,NULL),(1730116086331883522,1,1,'aaasdfs\'fk ghiosu',1,'2023-11-30 14:46:33','2023-12-13 13:53:02',1,NULL),(1730549672671465473,1,1,'232222',1,'2023-12-01 19:29:28','2023-12-08 15:05:19',1,NULL),(1730550743028486146,1,2,'aaaa,che',1,'2023-12-01 19:33:43','2024-04-20 01:06:14',2,NULL),(1737012985294827521,2,1,'aa',0,'2023-12-19 15:32:22','2023-12-19 15:32:22',1,NULL),(1737013797941227521,1737013516335656962,1,'aa',0,'2023-12-19 15:35:36','2023-12-19 15:35:36',1,NULL),(1781329944768847873,1781329259293745154,1,'占座',0,'2024-04-19 22:32:09','2024-04-19 22:32:09',1,NULL),(1781365676635987969,1781329259293745154,1,'233',0,'2024-04-20 00:54:08','2024-04-20 00:54:08',1,NULL),(1781369569646354433,1781328742400303106,2,'好看',0,'2024-04-20 01:09:36','2024-04-20 01:09:36',2,NULL),(1781369586981412866,1781328742400303106,2,'冲冲冲',0,'2024-04-20 01:09:40','2024-04-20 01:09:40',2,NULL),(1781369669189771266,1781328147312451586,2,'你好，你好，你好',0,'2024-04-20 01:10:00','2024-04-20 01:10:00',2,NULL),(1781373287485808642,1781329259293745154,1,'2333',0,'2024-04-20 01:24:22','2024-04-20 01:24:22',1,NULL),(1781374873696083969,1781329259293745154,1,'223',0,'2024-04-20 01:30:40','2024-04-20 01:30:40',1,NULL);
/*!40000 ALTER TABLE `opera_video_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playgoer`
--

DROP TABLE IF EXISTS `playgoer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playgoer` (
  `playgoer_id` bigint NOT NULL COMMENT '戏迷编号',
  `playgoer_name` varchar(20) COLLATE utf8mb4_0900_bin DEFAULT NULL COMMENT '戏迷用户名（4-20位）',
  `playgoer_info` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin DEFAULT '系统原装签名，送给每一个小可爱' COMMENT '戏迷个人简介',
  `playgoer_sex` int DEFAULT '0' COMMENT '戏迷性别 （0 保密 1 男 2 女）',
  `playgoer_birthday` date DEFAULT NULL COMMENT '戏迷出生年月',
  `playgoer_avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin DEFAULT NULL COMMENT '戏迷头像ID',
  `is_delete` int DEFAULT '0' COMMENT '是否删除',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` bigint DEFAULT NULL COMMENT '创建人',
  `updated_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`playgoer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin COMMENT='戏迷个人信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playgoer`
--

LOCK TABLES `playgoer` WRITE;
/*!40000 ALTER TABLE `playgoer` DISABLE KEYS */;
INSERT INTO `playgoer` VALUES (1,'张三','你好',1,'2023-11-14','https://opera-all.oss-cn-beijing.aliyuncs.com/images/b381f537-1f92-442d-a0f0-405be2f9c498.jpg',0,'2023-11-22 10:40:45','2023-12-16 19:57:05',NULL,1),(2,'李四','系统原装签名，送给每一个小可爱',0,'2024-04-18','https://opera-all.oss-cn-beijing.aliyuncs.com/images/84097ecb-8018-4d32-9110-f5b9e3195ac3.jpg',0,'2023-11-23 15:46:17','2024-04-20 01:33:25',NULL,2),(1736579039988457473,'王五','系统原装签名，送给每一个小可爱',0,NULL,NULL,0,'2023-12-18 10:48:02','2023-12-18 11:02:16',NULL,NULL),(1736585874632077314,'自觉的过客','系统原装签名，送给每一个小可爱',0,NULL,NULL,0,'2023-12-18 11:15:11','2023-12-18 11:15:11',NULL,NULL);
/*!40000 ALTER TABLE `playgoer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playgoer_activity`
--

DROP TABLE IF EXISTS `playgoer_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playgoer_activity` (
  `playgoer_activity_id` bigint NOT NULL COMMENT '戏迷活动编号',
  `activity_id` bigint NOT NULL COMMENT '活动编号',
  `playgoer_id` bigint NOT NULL COMMENT '戏迷编号',
  `is_delete` int DEFAULT '0' COMMENT '是否删除 （默认0 未删除 1 已删除）',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` bigint DEFAULT NULL COMMENT '创建人',
  `updated_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`playgoer_activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin COMMENT='戏迷活动关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playgoer_activity`
--

LOCK TABLES `playgoer_activity` WRITE;
/*!40000 ALTER TABLE `playgoer_activity` DISABLE KEYS */;
INSERT INTO `playgoer_activity` VALUES (1,1,1,1,'2023-12-05 14:34:33','2023-12-07 15:24:49',1,NULL),(2,1,2,0,'2023-12-05 14:49:03','2023-12-05 14:49:03',2,NULL),(1731953342256549890,2,1,1,'2023-12-05 16:27:09','2023-12-07 15:24:16',NULL,NULL),(1732662669011329025,1,1,1,'2023-12-07 15:25:46','2023-12-07 15:36:51',NULL,NULL),(1732662672811368450,2,1,1,'2023-12-07 15:25:47','2023-12-07 15:35:51',NULL,NULL),(1732665474530963458,1,1,1,'2023-12-07 15:36:55','2023-12-07 15:42:26',NULL,NULL),(1732665476670058497,2,1,1,'2023-12-07 15:36:55','2023-12-07 15:37:05',NULL,NULL),(1732667187107229698,1,1,1,'2023-12-07 15:43:43','2023-12-08 15:30:44',NULL,NULL),(1732667190185848833,2,1,1,'2023-12-07 15:43:44','2023-12-08 15:30:43',NULL,NULL),(1733032046789513218,1,1,1,'2023-12-08 15:53:32','2024-04-06 15:29:44',NULL,NULL),(1733032105576878081,2,1,1,'2023-12-08 15:53:46','2023-12-14 16:03:24',NULL,NULL),(1735641531659493377,1735635785551118337,1,1,'2023-12-15 20:42:42','2024-03-18 14:17:04',NULL,NULL),(1736925712407818241,1735630192908099586,1,1,'2023-12-19 09:45:35','2024-03-29 09:59:10',NULL,NULL),(1737012567768666114,1735630478049468417,1,1,'2023-12-19 15:30:42','2024-03-29 09:59:08',NULL,NULL),(1773530215168946177,2,1,1,'2024-03-29 09:58:48','2024-04-06 15:29:46',NULL,NULL),(1773530252158513153,1735635785551118337,1,1,'2024-03-29 09:58:57','2024-03-29 09:59:07',NULL,NULL),(1776512515724087297,1735635785551118337,1,1,'2024-04-06 15:29:24','2024-04-06 15:29:45',NULL,NULL),(1776513901085921282,1735635785551118337,1,0,'2024-04-06 15:34:54','2024-04-06 15:34:54',NULL,NULL),(1776513935957364738,1735630478049468417,1,0,'2024-04-06 15:35:03','2024-04-06 15:35:03',NULL,NULL),(1779529361037283329,1735635785551118337,2,1,'2024-04-14 23:17:16','2024-04-20 01:33:05',NULL,NULL),(1779529364698910721,1735630478049468417,2,0,'2024-04-14 23:17:17','2024-04-14 23:17:17',NULL,NULL),(1781365744390815745,1,1,0,'2024-04-20 00:54:24','2024-04-20 00:54:24',NULL,NULL),(1781371551434063873,2,1,0,'2024-04-20 01:17:28','2024-04-20 01:17:28',NULL,NULL),(1781373438208163841,1735630192908099586,1,0,'2024-04-20 01:24:58','2024-04-20 01:24:58',NULL,NULL);
/*!40000 ALTER TABLE `playgoer_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_account`
--

DROP TABLE IF EXISTS `sys_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_account` (
  `account_id` bigint NOT NULL,
  `account_username` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin NOT NULL COMMENT '戏迷登录用户名（4-16位）',
  `account_password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin NOT NULL COMMENT '戏迷登录密码（4-16位）\r\n',
  `account_statue` int NOT NULL DEFAULT '0' COMMENT '账号状态 （默认0 未禁用 1已禁用）',
  `playgoer_id` bigint DEFAULT NULL COMMENT '戏迷个人信息编号',
  `is_delete` int DEFAULT '0' COMMENT '是否删除 （默认0 未删除 1已删除）',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '账号创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '账号更新时间',
  `created_by` bigint DEFAULT NULL COMMENT '创建人',
  `updated_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin COMMENT='戏迷账号表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_account`
--

LOCK TABLES `sys_account` WRITE;
/*!40000 ALTER TABLE `sys_account` DISABLE KEYS */;
INSERT INTO `sys_account` VALUES (1,'admin','21232f297a57a5a743894a0e4a801fc3',0,1,0,'2023-11-22 10:37:36','2023-12-18 11:21:40',NULL,NULL),(2,'123456','e10adc3949ba59abbe56e057f20f883e',0,2,0,'2023-11-23 15:45:48','2023-12-18 11:22:35',NULL,NULL),(1736579132204417026,'123','202cb962ac59075b964b07152d234b70',0,1736579039988457473,0,'2023-12-18 10:48:23','2023-12-18 10:48:23',NULL,NULL),(1736585920169558018,'1234','81dc9bdb52d04dc20036dbd8313ed055',0,1736585874632077314,0,'2023-12-18 11:15:11','2023-12-18 11:15:22',NULL,NULL);
/*!40000 ALTER TABLE `sys_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_account_role`
--

DROP TABLE IF EXISTS `sys_account_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_account_role` (
  `account_role_id` bigint NOT NULL COMMENT '账号角色编号',
  `account_id` bigint NOT NULL COMMENT '账号编号',
  `role_id` bigint NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`account_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin COMMENT='账号角色关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_account_role`
--

LOCK TABLES `sys_account_role` WRITE;
/*!40000 ALTER TABLE `sys_account_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_account_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu_item`
--

DROP TABLE IF EXISTS `sys_menu_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_menu_item` (
  `item_id` bigint NOT NULL COMMENT '权限编号',
  `item_remark` varchar(20) COLLATE utf8mb4_0900_bin DEFAULT NULL COMMENT '权限描述',
  `item_url` varchar(30) COLLATE utf8mb4_0900_bin DEFAULT NULL COMMENT '权限路径',
  `is_delete` int DEFAULT '0' COMMENT '是否删除（默认 0 未删除 1 已删除）',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` bigint DEFAULT NULL COMMENT '创建人',
  `updated_by` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu_item`
--

LOCK TABLES `sys_menu_item` WRITE;
/*!40000 ALTER TABLE `sys_menu_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_menu_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role` (
  `role_id` bigint NOT NULL COMMENT '角色编号',
  `role_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin NOT NULL COMMENT '角色名',
  `role_remark` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin NOT NULL COMMENT '角色描述',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sys_role_menu` (
  `role_menu_id` bigint NOT NULL COMMENT '角色权限编号',
  `role_id` bigint NOT NULL COMMENT '角色编号',
  `menu_id` bigint NOT NULL COMMENT '权限编号',
  PRIMARY KEY (`role_menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_bin COMMENT='角色权限关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-24 11:07:10
