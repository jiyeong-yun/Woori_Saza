-- MySQL dump 10.19  Distrib 10.3.32-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: woorisaza
-- ------------------------------------------------------
-- Server version	10.3.32-MariaDB-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `article_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) DEFAULT NULL,
  `content` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL,
  `link` varchar(1000) NOT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `party_id` bigint(20) DEFAULT NULL,
  `profile_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`article_id`),
  UNIQUE KEY `UK_l8utwgyx74dj5y68vt84p7wrk` (`party_id`),
  KEY `FKooa57fus516b7k3vputh8w3p6` (`profile_id`),
  CONSTRAINT `FK80njo021hshbeu7jnbfju8ihj` FOREIGN KEY (`party_id`) REFERENCES `party` (`party_id`),
  CONSTRAINT `FKooa57fus516b7k3vputh8w3p6` FOREIGN KEY (`profile_id`) REFERENCES `user_profile` (`profile_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1,'FOOD','탄산수 싸게 나와서 다음주 전에 같이 사실 분 구합니다\n!!','2022-02-17 16:51:35','https://smartstore.naver.com/bebenaire/products/5509452683?NaPm=ct%3Dkzqoowko%7Cci%3Dfc60607486f0fa712f6b50da0268101167fbbbf5%7Ctr%3Dslcc%7Csn%3D2402549%7Chk%3D250e0a3df37ee380c3ae12e1dc62fe664b7eed96',NULL,'탄산수 행사하네요',1,'663a0d47b358f9de4c91c35afd0b55adde3e810328c21b91d99025cc1dc919fa'),(3,'DELIVERY','불금엔 치킨이죠! 호식이 두마리 치킨 시켜서 한마리씩 나눠먹어요~!\n\n배달비 엔빵 개꿀!','2022-02-17 16:56:58','배달링크는 없어요!',NULL,'치킨 먹어요 치킨!',3,'663a0d47b358f9de4c91c35afd0b55adde3e810328c21b91d99025cc1dc919fa'),(4,'LIFETHINGS','저는 정말 유용하게 쓰고 있어요!\n함께 사요~','2022-02-17 16:58:47','https://shopping.naver.com/market/necessity/products/6181386497?NaPm=ct%3Dkzqowhil%7Cci%3Dshoppingwindow%7Ctr%3Dnct%7Chk%3D066164ae8f1352a423054d3ca0e4c7c68345ad98%7Ctrx%3D',NULL,'베이킹소다 같이 사요!',4,'621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(5,'FOOD','이거 정말 맛있어요!','2022-02-17 17:01:38','https://shopping.naver.com/market/necessity/products/6172331151?NaPm=ct%3Dkzqp1ksr%7Cci%3Dshoppingwindow%7Ctr%3Dnct%7Chk%3D47fec830ad90426c7393e9866f19660e759fe272%7Ctrx%3D',NULL,'비엔나 소시지! 한봉씩 나눠요~',5,'621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(6,'ETC','5개 짜리인데 4만원 이상이 무료배송이라 2개 시켜서 나누실분~','2022-02-17 17:04:55','https://shopping.naver.com/pet/stores/100854783/products/6308807742?NaPm=ci%3Dshoppingwindow%7Cct%3Ddummy%7Ctr%3Dswl%7Chk%3D3271aa60b1cb27830dbc77a8cb4c628ac3066de1%7Ctrx%3D6308807742',NULL,'강아지 수제간식! (배송비 아껴요~)',6,'621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(8,'LIFETHINGS','물티슈 10개씩 나눠요~','2022-02-17 17:11:40','https://smartstore.naver.com/gcleantec/products/3232061815?n_media=11068&n_rank=6&n_ad_group=grp-a001-02-000000022931175&n_ad=nad-a001-02-000000147809513&n_campaign_type=2&n_mall_id=cyu5118&n_mall_pid=3232061815&n_ad_group_type=2&NaPm=ct%3Dkzqpc9mg%7Cci%3D0zW0002x0uTwBRiYW0YV%7Ctr%3Dpla%7Chk%3D6b24026a93d1473fc3b889f186bd080a6836ed0e',NULL,'물티슈',8,'621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(9,'FASHION','양말 흰검10켤레씩 사서 반반 나눠요~','2022-02-17 17:15:27','https://www.coupang.com/vp/products/1806346903?itemId=3073576081&vendorItemId=71061481453&q=%EC%96%91%EB%A7%90&itemsCount=36&searchId=3461aab713fc4085a5e80aa5224480e7&rank=3&isAddedCart=',NULL,'양말 흰검 5켤레씩 나눕시다',9,'621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(10,'FOOD','같이 구매하실분 구합니다','2022-02-17 17:27:34','https://search.shopping.naver.com/catalog/12107376356?NaPm=ct%3Dkzqormkg%7Cci%3Dc8346ff311e2a5031aadc639ff6619bd8d0f17ce%7Ctr%3Dslcc%7Csn%3D95694%7Chk%3D17f745d74bd655db8ff937d27b345a28c9da2f06',NULL,'커피 공구해요',10,'7ee3600ac2f353b0e73cedf6b680b53a2034519f611cb164040fd438b7651a6b'),(11,'DELIVERY','지금 바로!!!','2022-02-17 17:32:31','배달링크는 없어요!',NULL,'지금 바로 피자 1+1 드실분!!!!',11,'621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(14,'FOOD','ㅎㅇ','2022-02-17 18:06:34','https://search.shopping.naver.com/catalog/30286780517?query=%EB%96%A1%EB%B3%B6%EC%9D%B4&NaPm=ct%3Dkzqr0eqw%7Cci%3Db176cddc6a59971bc63cfc8e372f17aa4e9677d3%7Ctr%3Dslsl%7Csn%3D95694%7Chk%3D370252b9686d82494ae26ee57529e745e5d31f2b',NULL,'떡볶이 드실?',14,NULL),(16,'FOOD','ㅎㅇㅎㅇㅎㅇ','2022-02-17 18:25:45','https://smartstore.naver.com/rankingdak/products/4817689643?NaPm=ct%3Dkzqs1nso%7Cci%3D0yC0001x50TwO89Z1LnJ%7Ctr%3Dpla%7Chk%3D9ba849394e21295680579e90ea266c521c62d3a2',NULL,'떡볶이 드실?',16,NULL),(17,'FOOD','제주 감귤','2022-02-17 18:42:30','https://smartstore.naver.com/goodvip/products/5184853256?NaPm=ct%3Dkzqro1ts%7Cci%3D1c766aeb63aaab05788a4eeb0de0faf46eae80c4%7Ctr%3Dslcc%7Csn%3D1730066%7Chk%3De3d095380d160e043813e570335c45378239063f',NULL,'제주 감귤',17,'663a0d47b358f9de4c91c35afd0b55adde3e810328c21b91d99025cc1dc919fa');
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_pic`
--

DROP TABLE IF EXISTS `article_pic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_pic` (
  `article_article_id` bigint(20) NOT NULL,
  `pic` varchar(255) DEFAULT NULL,
  KEY `FK6g0bl56rch3gnfbkq7ojns9xe` (`article_article_id`),
  CONSTRAINT `FK6g0bl56rch3gnfbkq7ojns9xe` FOREIGN KEY (`article_article_id`) REFERENCES `article` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_pic`
--

LOCK TABLES `article_pic` WRITE;
/*!40000 ALTER TABLE `article_pic` DISABLE KEYS */;
INSERT INTO `article_pic` VALUES (1,'https://shop-phinf.pstatic.net/20210407_7/1617799347439JUTrH_JPEG/18935235953269057_1677501309.jpg?type=o640'),(4,'https://shop-phinf.pstatic.net/20220112_16/1641964494981iFFuX_JPEG/43100278689762746_759903723.jpg?type=m450'),(5,'https://shop-phinf.pstatic.net/20220110_90/1641779985711pGyWE_JPEG/101691_12.jpg?type=m450'),(6,'https://shop-phinf.pstatic.net/20211019_52/1634623202357jDmOi_JPEG/35759036978506325_36138274.jpg?type=m450'),(3,'https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FnMjVP%2FbtrtsVDTjqZ%2FAKAjyysiZXL0JdtlJS8kr0%2Fimg.png'),(3,'https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FnMjVP%2FbtrtsVDTjqZ%2FAKAjyysiZXL0JdtlJS8kr0%2Fimg.png'),(3,'https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FnMjVP%2FbtrtsVDTjqZ%2FAKAjyysiZXL0JdtlJS8kr0%2Fimg.png'),(3,'https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FnMjVP%2FbtrtsVDTjqZ%2FAKAjyysiZXL0JdtlJS8kr0%2Fimg.png'),(3,'https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FnMjVP%2FbtrtsVDTjqZ%2FAKAjyysiZXL0JdtlJS8kr0%2Fimg.png'),(3,'https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FnMjVP%2FbtrtsVDTjqZ%2FAKAjyysiZXL0JdtlJS8kr0%2Fimg.png'),(3,'https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FnMjVP%2FbtrtsVDTjqZ%2FAKAjyysiZXL0JdtlJS8kr0%2Fimg.png'),(8,'https://shop-phinf.pstatic.net/20211201_199/1638345747609262Oa_JPEG/39481643285401095_1043525258.jpg?type=o640'),(9,'https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F0654c%2Fbtrtt5lVhlq%2FouTPxWDaPGuOqSRE5QWQdk%2Fimg.png'),(11,'https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FnMjVP%2FbtrtsVDTjqZ%2FAKAjyysiZXL0JdtlJS8kr0%2Fimg.png'),(10,'https://shopping-phinf.pstatic.net/main_1210737/12107376356.20210127164940.jpg?type=f160'),(14,'https://shopping-phinf.pstatic.net/main_3028678/30286780517.20211225004100.jpg?type=f160'),(16,'https://shop-phinf.pstatic.net/20200211_233/1581404906620IoApw_JPEG/18767240180187032_153847846.JPG?type=o640'),(17,'https://shop-phinf.pstatic.net/20220119_162/1642576192833WfkoK_JPEG/43712088260374049_948325587.jpg?type=o640');
/*!40000 ALTER TABLE `article_pic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_message`
--

DROP TABLE IF EXISTS `chat_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_message` (
  `msg_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `sender` varchar(255) NOT NULL,
  `time` varchar(255) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `room_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`msg_id`),
  KEY `FKfvbc4wvhk51y0qtnjrbminxfu` (`room_id`),
  CONSTRAINT `FKfvbc4wvhk51y0qtnjrbminxfu` FOREIGN KEY (`room_id`) REFERENCES `chat_room` (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_message`
--

LOCK TABLES `chat_message` WRITE;
/*!40000 ALTER TABLE `chat_message` DISABLE KEYS */;
INSERT INTO `chat_message` VALUES (1,'감사합니다^^','지제로','2022.02.17 17:16','CHAT','ec2974a0-76bf-4d3d-968f-627eeee94021'),(2,'저요!','지제로','2022.02.17 17:16','CHAT','0aa081ec-6704-4617-9e41-846d1eb68647'),(3,'강아지가 엄청 좋아해요 감사합니다~','지제로','2022.02.17 17:16','CHAT','725f9140-34ce-4ec1-a520-56a12623c1b0'),(4,'안녕하세요!','Moz1e','2022.02.17 17:23','CHAT','0aa081ec-6704-4617-9e41-846d1eb68647'),(5,'토요일 9시에 글린공원 어떠신가요?','Moz1e','2022.02.17 17:24','CHAT','0aa081ec-6704-4617-9e41-846d1eb68647'),(6,'9시는 조금 그렇고 7시 정도 어떤가요?','지제로','2022.02.17 17:24','CHAT','0aa081ec-6704-4617-9e41-846d1eb68647'),(7,'네네 7시도 가능합니다!','Moz1e','2022.02.17 17:24','CHAT','0aa081ec-6704-4617-9e41-846d1eb68647'),(8,'그러면 그때 봐요~','Moz1e','2022.02.17 17:25','CHAT','0aa081ec-6704-4617-9e41-846d1eb68647'),(9,'구매확정 눌러주세요','Moz1e','2022.02.17 17:25','CHAT','0aa081ec-6704-4617-9e41-846d1eb68647'),(10,'네~ 만나서 눌러드릴게요^^!','지제로','2022.02.17 17:25','CHAT','0aa081ec-6704-4617-9e41-846d1eb68647'),(11,'저희 집 강아지가 포메라니안인데','Moz1e','2022.02.17 17:42','CHAT','725f9140-34ce-4ec1-a520-56a12623c1b0'),(12,'좋아할까요?','Moz1e','2022.02.17 17:42','CHAT','725f9140-34ce-4ec1-a520-56a12623c1b0'),(13,'이거 맛은 어떤 맛이에요?','Moz1e','2022.02.17 17:42','CHAT','725f9140-34ce-4ec1-a520-56a12623c1b0'),(14,'환장할겁니다','지제로','2022.02.17 17:42','CHAT','725f9140-34ce-4ec1-a520-56a12623c1b0'),(15,'밥이랑 먹으면 맛있을까요?','Moz1e','2022.02.17 17:42','CHAT','725f9140-34ce-4ec1-a520-56a12623c1b0'),(16,'제가 보장합니다','지제로','2022.02.17 17:42','CHAT','725f9140-34ce-4ec1-a520-56a12623c1b0'),(17,'아하 감사합니다','Moz1e','2022.02.17 17:42','CHAT','725f9140-34ce-4ec1-a520-56a12623c1b0'),(18,'근데 안살래요 ㅋㅋ','Moz1e','2022.02.17 17:42','CHAT','725f9140-34ce-4ec1-a520-56a12623c1b0'),(19,'ㅅㄱㅇ','Moz1e','2022.02.17 17:42','CHAT','725f9140-34ce-4ec1-a520-56a12623c1b0'),(20,'야!','지제로','2022.02.17 17:42','CHAT','725f9140-34ce-4ec1-a520-56a12623c1b0'),(21,'안녕하세요~','Moz1e','2022.02.17 17:57','CHAT','de3d3b2e-c72e-4c5f-a5d6-02c87805a746'),(22,'안녕하세요^^','지제로','2022.02.17 17:58','CHAT','de3d3b2e-c72e-4c5f-a5d6-02c87805a746'),(23,'안녕~!','영하','2022.02.17 17:58','CHAT','de3d3b2e-c72e-4c5f-a5d6-02c87805a746'),(24,'안녕하세요~','Moz1e','2022.02.17 18:29','CHAT','63fa6bc1-e005-4148-b437-cf19cfdaa72a'),(25,'안녕하세요~','영하','2022.02.17 18:29','CHAT','63fa6bc1-e005-4148-b437-cf19cfdaa72a'),(26,'만날 시간 만날 장소 정해봐요','영하','2022.02.17 18:29','CHAT','63fa6bc1-e005-4148-b437-cf19cfdaa72a'),(27,'감귤 꿀맛','Moz1e','2022.02.17 18:29','CHAT','63fa6bc1-e005-4148-b437-cf19cfdaa72a');
/*!40000 ALTER TABLE `chat_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_room`
--

DROP TABLE IF EXISTS `chat_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_room` (
  `room_id` varchar(255) NOT NULL,
  `article_id` bigint(20) DEFAULT NULL,
  `count` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_room`
--

LOCK TABLES `chat_room` WRITE;
/*!40000 ALTER TABLE `chat_room` DISABLE KEYS */;
INSERT INTO `chat_room` VALUES ('0a0f54ba-bf84-463c-b4f6-1bf46df7b651',3,1,'치킨 먹어요 치킨!'),('0aa081ec-6704-4617-9e41-846d1eb68647',9,2,'양말 흰검 5켤레씩 나눕시다'),('518d6e61-b33b-4606-ba65-68d2456986b8',2,1,'카누 미니 마일드 나눠요'),('63fa6bc1-e005-4148-b437-cf19cfdaa72a',15,2,'감귤 사요'),('725f9140-34ce-4ec1-a520-56a12623c1b0',6,1,'강아지 수제간식! (배송비 아껴요~)'),('77a70df3-8dce-45d9-bfc2-2680813d3f14',7,1,'title'),('78dd143e-2067-412f-a0cc-0d027e0c01e5',17,1,'제주 감귤'),('7da4806a-c536-490c-9c95-c5e802875b70',4,1,'베이킹소다 같이 사요!'),('90bbc697-b060-4c03-9f3b-7450d99450b5',14,2,'떡볶이 드실?'),('9b298b42-f0ea-47c1-a019-cbc1e114830a',1,1,'탄산수 행사하네요'),('d7d1792c-c497-4060-932c-f1d14e8b19ef',11,2,'지금 바로 피자 1+1 드실분!!!!'),('dd9fe761-1845-461c-9904-312f695f83c3',16,2,'떡볶이 드실?'),('de3d3b2e-c72e-4c5f-a5d6-02c87805a746',12,3,'제주감귤 1kg씩 나누기'),('ec2974a0-76bf-4d3d-968f-627eeee94021',5,1,'비엔나 소시지! 한봉씩 나눠요~'),('eeebabe4-d8bc-44b4-a9e0-e5a20c429999',10,2,'커피 공구해요'),('f22cfb8a-3808-499c-a4b5-b3b6599ce0ea',8,2,'물티슈'),('f527a79f-5b89-4096-b073-74a631fdd16e',13,1,'떡볶이 드실?');
/*!40000 ALTER TABLE `chat_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_room_join`
--

DROP TABLE IF EXISTS `chat_room_join`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat_room_join` (
  `join_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `room_id` varchar(255) DEFAULT NULL,
  `profile_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`join_id`),
  KEY `FK5jret1ares1oeroir4idj4hyn` (`room_id`),
  KEY `FKp0ob93lij2xnu6jf8cf4uvuh9` (`profile_id`),
  CONSTRAINT `FK5jret1ares1oeroir4idj4hyn` FOREIGN KEY (`room_id`) REFERENCES `chat_room` (`room_id`),
  CONSTRAINT `FKp0ob93lij2xnu6jf8cf4uvuh9` FOREIGN KEY (`profile_id`) REFERENCES `user_profile` (`profile_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_room_join`
--

LOCK TABLES `chat_room_join` WRITE;
/*!40000 ALTER TABLE `chat_room_join` DISABLE KEYS */;
INSERT INTO `chat_room_join` VALUES (1,'9b298b42-f0ea-47c1-a019-cbc1e114830a','663a0d47b358f9de4c91c35afd0b55adde3e810328c21b91d99025cc1dc919fa'),(2,'518d6e61-b33b-4606-ba65-68d2456986b8','7ee3600ac2f353b0e73cedf6b680b53a2034519f611cb164040fd438b7651a6b'),(3,'0a0f54ba-bf84-463c-b4f6-1bf46df7b651','663a0d47b358f9de4c91c35afd0b55adde3e810328c21b91d99025cc1dc919fa'),(4,'7da4806a-c536-490c-9c95-c5e802875b70','621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(5,'ec2974a0-76bf-4d3d-968f-627eeee94021','621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(6,'725f9140-34ce-4ec1-a520-56a12623c1b0','621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(7,'77a70df3-8dce-45d9-bfc2-2680813d3f14','7ee3600ac2f353b0e73cedf6b680b53a2034519f611cb164040fd438b7651a6b'),(8,'f22cfb8a-3808-499c-a4b5-b3b6599ce0ea','621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(9,'0aa081ec-6704-4617-9e41-846d1eb68647','621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(10,'0aa081ec-6704-4617-9e41-846d1eb68647','663a0d47b358f9de4c91c35afd0b55adde3e810328c21b91d99025cc1dc919fa'),(11,'eeebabe4-d8bc-44b4-a9e0-e5a20c429999','7ee3600ac2f353b0e73cedf6b680b53a2034519f611cb164040fd438b7651a6b'),(12,'d7d1792c-c497-4060-932c-f1d14e8b19ef','621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(13,'f22cfb8a-3808-499c-a4b5-b3b6599ce0ea','00aaeacf898a2c2d1fe18f1b6adb62491404a7638ba7e27d0735d527e40f1737'),(14,'d7d1792c-c497-4060-932c-f1d14e8b19ef','00aaeacf898a2c2d1fe18f1b6adb62491404a7638ba7e27d0735d527e40f1737'),(15,'eeebabe4-d8bc-44b4-a9e0-e5a20c429999','00aaeacf898a2c2d1fe18f1b6adb62491404a7638ba7e27d0735d527e40f1737'),(16,'de3d3b2e-c72e-4c5f-a5d6-02c87805a746','663a0d47b358f9de4c91c35afd0b55adde3e810328c21b91d99025cc1dc919fa'),(18,'de3d3b2e-c72e-4c5f-a5d6-02c87805a746','621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(22,'90bbc697-b060-4c03-9f3b-7450d99450b5','621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(23,'63fa6bc1-e005-4148-b437-cf19cfdaa72a','663a0d47b358f9de4c91c35afd0b55adde3e810328c21b91d99025cc1dc919fa'),(25,'dd9fe761-1845-461c-9904-312f695f83c3','621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(27,'78dd143e-2067-412f-a0cc-0d027e0c01e5','663a0d47b358f9de4c91c35afd0b55adde3e810328c21b91d99025cc1dc919fa');
/*!40000 ALTER TABLE `chat_room_join` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `create_at` datetime NOT NULL,
  `article_id` bigint(20) DEFAULT NULL,
  `profile_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FK5yx0uphgjc6ik6hb82kkw501y` (`article_id`),
  KEY `FKkjr5y9mbu2gqteek4xt7c3nm1` (`profile_id`),
  CONSTRAINT `FK5yx0uphgjc6ik6hb82kkw501y` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`),
  CONSTRAINT `FKkjr5y9mbu2gqteek4xt7c3nm1` FOREIGN KEY (`profile_id`) REFERENCES `user_profile` (`profile_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (3,'ㅅㅅㅅㅅㅅㅅ','2022-02-17 17:05:48',6,'c945b7cd33248e4ef03b936ac3ca3854425cc7376d3be59d9fb6d6cdd6e93b4b'),(4,'사람이 먹을건 못되네요','2022-02-17 17:06:16',6,'e6411e18f543766420a6a2b354c2834d1ea613f63187fa51419b675f28a32da7'),(5,'멍멍!','2022-02-17 17:09:24',6,'663a0d47b358f9de4c91c35afd0b55adde3e810328c21b91d99025cc1dc919fa'),(6,'물티슈 필요했는데 파티 참여합니다! 채팅 ㄱㄱ','2022-02-17 17:12:58',8,'663a0d47b358f9de4c91c35afd0b55adde3e810328c21b91d99025cc1dc919fa'),(7,'안녕하세요','2022-02-17 17:36:14',11,'00aaeacf898a2c2d1fe18f1b6adb62491404a7638ba7e27d0735d527e40f1737'),(8,'구경왔어요~','2022-02-17 17:36:28',8,'00aaeacf898a2c2d1fe18f1b6adb62491404a7638ba7e27d0735d527e40f1737'),(9,'너무 맛있겠네요!','2022-02-17 18:07:21',14,'621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(10,'신청했습니다 너무 기대되네요!!','2022-02-17 18:26:12',16,'621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_info`
--

DROP TABLE IF EXISTS `member_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_info` (
  `member_info_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NOT NULL,
  `is_boss` tinyint(1) DEFAULT 0,
  `is_confirmed` tinyint(1) DEFAULT 0,
  `is_lated` tinyint(1) DEFAULT 0,
  `paid_method` varchar(255) DEFAULT NULL,
  `price` int(11) NOT NULL,
  `party_id` bigint(20) DEFAULT NULL,
  `profile_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`member_info_id`),
  KEY `FKe0ixp8qx9df5dn5476q0mj4lc` (`party_id`),
  KEY `FKie73p2y5xalsxohuq8vmpfkpy` (`profile_id`),
  CONSTRAINT `FKe0ixp8qx9df5dn5476q0mj4lc` FOREIGN KEY (`party_id`) REFERENCES `party` (`party_id`),
  CONSTRAINT `FKie73p2y5xalsxohuq8vmpfkpy` FOREIGN KEY (`profile_id`) REFERENCES `user_profile` (`profile_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_info`
--

LOCK TABLES `member_info` WRITE;
/*!40000 ALTER TABLE `member_info` DISABLE KEYS */;
INSERT INTO `member_info` VALUES (1,1,1,0,0,NULL,7950,1,'663a0d47b358f9de4c91c35afd0b55adde3e810328c21b91d99025cc1dc919fa'),(2,3,1,0,0,NULL,7500,2,'7ee3600ac2f353b0e73cedf6b680b53a2034519f611cb164040fd438b7651a6b'),(3,1,1,0,0,NULL,24000,3,'663a0d47b358f9de4c91c35afd0b55adde3e810328c21b91d99025cc1dc919fa'),(4,1,1,0,0,NULL,7633,4,'621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(5,1,1,0,0,NULL,5450,5,'621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(6,1,1,0,0,NULL,25170,6,'621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(7,3,1,0,0,NULL,1500,7,'7ee3600ac2f353b0e73cedf6b680b53a2034519f611cb164040fd438b7651a6b'),(8,1,1,0,0,NULL,11450,8,'621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(9,1,1,0,0,NULL,9760,9,'621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(10,1,0,0,0,'kakao',9760,9,'663a0d47b358f9de4c91c35afd0b55adde3e810328c21b91d99025cc1dc919fa'),(11,2,1,0,0,NULL,11532,10,'7ee3600ac2f353b0e73cedf6b680b53a2034519f611cb164040fd438b7651a6b'),(12,1,1,0,0,NULL,21000,11,'621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(13,1,0,0,0,'kakao',11450,8,'00aaeacf898a2c2d1fe18f1b6adb62491404a7638ba7e27d0735d527e40f1737'),(14,1,0,0,0,'kakao',21000,11,'00aaeacf898a2c2d1fe18f1b6adb62491404a7638ba7e27d0735d527e40f1737'),(15,1,0,0,0,'kakao',5766,10,'00aaeacf898a2c2d1fe18f1b6adb62491404a7638ba7e27d0735d527e40f1737'),(16,1,1,0,0,NULL,8600,12,'663a0d47b358f9de4c91c35afd0b55adde3e810328c21b91d99025cc1dc919fa'),(18,1,0,0,0,'kakao',8600,12,'621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(22,1,0,1,0,'kakao',5000,14,'621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(23,1,1,0,0,NULL,8433,15,'663a0d47b358f9de4c91c35afd0b55adde3e810328c21b91d99025cc1dc919fa'),(25,1,0,1,0,'kakao',5000,16,'621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(27,1,1,0,0,NULL,8600,17,'663a0d47b358f9de4c91c35afd0b55adde3e810328c21b91d99025cc1dc919fa');
/*!40000 ALTER TABLE `member_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paid_form`
--

DROP TABLE IF EXISTS `paid_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paid_form` (
  `paid_form_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `billing_no` varchar(255) DEFAULT NULL,
  `delivery_date` date DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `receipt_date` date DEFAULT NULL,
  `party_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`paid_form_id`),
  UNIQUE KEY `UK_isy0ufdhygio3rlgxi3ijyyip` (`party_id`),
  CONSTRAINT `FKf9utq0a8b3dnxrm6lgusm6hvj` FOREIGN KEY (`party_id`) REFERENCES `party` (`party_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paid_form`
--

LOCK TABLES `paid_form` WRITE;
/*!40000 ALTER TABLE `paid_form` DISABLE KEYS */;
INSERT INTO `paid_form` VALUES (1,'123123123123','2022-02-19','http://i6c102.p.ssafy.io/images/paidform/5da6164f-bda4-4cbc-96f3-488898db412c.PNG','2022-02-21',14),(2,'213123213','2022-02-18','http://i6c102.p.ssafy.io/images/paidform/1ae99c03-a083-4190-b353-19c7d58a5f7b.JPG','2022-02-25',12),(3,'123123123','2022-02-18','http://i6c102.p.ssafy.io/images/paidform/e120d4a5-70f8-4a66-8fe7-9eeba9f1a015.PNG','2022-02-19',16),(4,'123213123','2022-02-18','http://i6c102.p.ssafy.io/images/paidform/26c3ddfb-5dcf-4bbe-91e2-fe179e8bfb39.JPG','2022-02-25',15);
/*!40000 ALTER TABLE `paid_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `party`
--

DROP TABLE IF EXISTS `party`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `party` (
  `party_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `current_recruit_member` int(11) NOT NULL,
  `deadline` datetime NOT NULL,
  `form_checked` tinyint(1) NOT NULL DEFAULT 0,
  `is_closed` tinyint(1) NOT NULL DEFAULT 0,
  `penalty` int(11) NOT NULL,
  `product` varchar(255) NOT NULL,
  `total_price` int(11) NOT NULL,
  `total_product_count` int(11) NOT NULL,
  `total_recruit_member` int(11) NOT NULL,
  PRIMARY KEY (`party_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `party`
--

LOCK TABLES `party` WRITE;
/*!40000 ALTER TABLE `party` DISABLE KEYS */;
INSERT INTO `party` VALUES (1,1,'2022-02-24 23:59:59',0,0,10,'빅토리아 탄산수',15900,40,2),(2,1,'2022-02-20 23:59:59',0,0,80,'product name',10000,24,4),(3,1,'2022-02-18 23:59:59',0,0,0,'호식이 두마리치킨',24000,2,2),(4,1,'2022-02-17 23:59:59',0,0,100,'옥시크린 오투액션 베이킹소다 500g',22900,3,3),(5,1,'2022-02-22 23:59:59',0,0,100,'하림 요리비엔나 1000g!',10900,2,2),(6,1,'2022-02-23 23:59:59',0,0,90,'포옹 - 테리셔스 올인원 50g',50340,10,2),(7,2,'2022-02-22 23:59:59',0,0,50,'product name',2000,24,4),(8,2,'2022-02-25 23:59:59',0,0,100,'미엘 물티슈 저자극!',22900,20,2),(9,2,'2022-02-24 23:59:59',0,0,80,'베이스알파에센셜 양말',19520,20,2),(10,3,'2022-02-24 23:59:59',0,0,20,'카누 미니',17300,1200,3),(11,2,'2022-02-17 23:59:59',0,0,100,'도미노 피자 1+1',42000,2,2),(12,3,'2022-02-25 23:59:59',1,1,10,'제주 감귤',25800,3,3),(13,1,'2022-02-19 23:59:59',0,1,50,'떡볶이',16000,2,2),(14,2,'2022-02-19 23:59:59',1,1,50,'떡볶이',10000,2,2),(15,2,'2022-02-24 23:59:59',1,1,10,'제주 감귤',25300,3,3),(16,2,'2022-02-19 23:59:59',1,1,50,'떡볶이',10000,2,2),(17,1,'2022-02-18 23:59:59',0,0,20,'제주 감귤',25800,3,3);
/*!40000 ALTER TABLE `party` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qna`
--

DROP TABLE IF EXISTS `qna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qna` (
  `qna_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category` varchar(255) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `content` varchar(255) NOT NULL,
  `date` datetime DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `profile_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`qna_id`),
  KEY `FKt1a4p7r9amgrjvung3i16ec6h` (`profile_id`),
  CONSTRAINT `FKt1a4p7r9amgrjvung3i16ec6h` FOREIGN KEY (`profile_id`) REFERENCES `user_profile` (`profile_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qna`
--

LOCK TABLES `qna` WRITE;
/*!40000 ALTER TABLE `qna` DISABLE KEYS */;
/*!40000 ALTER TABLE `qna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qna_pic`
--

DROP TABLE IF EXISTS `qna_pic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qna_pic` (
  `qna_qna_id` bigint(20) NOT NULL,
  `pic` varchar(255) DEFAULT NULL,
  KEY `FKkpvgmai7v51kgc8x0jk2awaru` (`qna_qna_id`),
  CONSTRAINT `FKkpvgmai7v51kgc8x0jk2awaru` FOREIGN KEY (`qna_qna_id`) REFERENCES `qna` (`qna_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qna_pic`
--

LOCK TABLES `qna_pic` WRITE;
/*!40000 ALTER TABLE `qna_pic` DISABLE KEYS */;
/*!40000 ALTER TABLE `qna_pic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `review_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `date` datetime DEFAULT NULL,
  `score` int(11) NOT NULL,
  `from_user_profile_id` varchar(255) DEFAULT NULL,
  `to_user_profile_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  KEY `FKj1nigpiwp853w1j6nsbfqfy1b` (`from_user_profile_id`),
  KEY `FKoh5fktrj4rfbc1rx1xekoxvyv` (`to_user_profile_id`),
  CONSTRAINT `FKj1nigpiwp853w1j6nsbfqfy1b` FOREIGN KEY (`from_user_profile_id`) REFERENCES `user_profile` (`profile_id`),
  CONSTRAINT `FKoh5fktrj4rfbc1rx1xekoxvyv` FOREIGN KEY (`to_user_profile_id`) REFERENCES `user_profile` (`profile_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_auth`
--

DROP TABLE IF EXISTS `user_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_auth` (
  `auth_id` varchar(255) NOT NULL,
  `is_admin` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_auth`
--

LOCK TABLES `user_auth` WRITE;
/*!40000 ALTER TABLE `user_auth` DISABLE KEYS */;
INSERT INTO `user_auth` VALUES ('0477f0d12a8c2e2d7721c671affc283ddd44359425bd0c74451b438447573eb1',0),('0bd5a69df78a9f9daf4b69eae7eeb64fbb46643a6a827c56dc4ab7a20e44447f',0),('1577d797d74a5e377f8a65a740da4866165a39d841af5ca82051d6080fa38ac4',1),('8e63d3fb5cc589ba7ddc9e428fdf63abec815fc30da83582c3438c7ccc23c720',0),('9aa994af1e0ccb9f787deb92825938b8678f1a434940604a32fcd0c3fd78a3e8',0),('9c3b478d98b080ba7673bb89c457980b9a6179f24add76d0572e591660cdab48',0),('c89c0a6044fdaac96f450a1f34e8ff4f992ebb15f805a3a3982f3fa5424b2285',0),('ed48ca0058018d723763c76db7980cf981b9f843670703b8c9a85fa2e6ad5cac',0);
/*!40000 ALTER TABLE `user_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_profile` (
  `profile_id` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `cnt` int(11) DEFAULT NULL,
  `join_date` datetime NOT NULL,
  `lat` double NOT NULL,
  `lng` double NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `auth_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`profile_id`),
  UNIQUE KEY `UK_m9ga0crhcge7onj1gx9a5lnjy` (`nickname`),
  UNIQUE KEY `UK_8hqxgxcn0r4purxf5k5ywhmlq` (`auth_id`),
  CONSTRAINT `FKjvlogxuhya3if7twwr2vs866y` FOREIGN KEY (`auth_id`) REFERENCES `user_auth` (`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` VALUES ('00aaeacf898a2c2d1fe18f1b6adb62491404a7638ba7e27d0735d527e40f1737','서울 서초구 동광로19길 10',0,'2022-02-17 17:34:34',37.4929244,126.9894796,'안녕하세요 구경왔어요~~',NULL,0,'9aa994af1e0ccb9f787deb92825938b8678f1a434940604a32fcd0c3fd78a3e8'),('621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81','경기 성남시 분당구 판교역로 235',2,'2022-02-17 16:56:35',37.4020589,127.1086288,'지제로','http://i6c102.p.ssafy.io/images/profile/be629e3f-b06c-482d-bfe1-6cbb9ac497c4.png',5,'c89c0a6044fdaac96f450a1f34e8ff4f992ebb15f805a3a3982f3fa5424b2285'),('663a0d47b358f9de4c91c35afd0b55adde3e810328c21b91d99025cc1dc919fa','경기 성남시 분당구 판교역로 6-2',1,'2022-02-17 16:49:46',37.3836967,127.1119594,'Moz1e','http://i6c102.p.ssafy.io/images/profile/6d793da8-270f-48f2-b448-ab9cf2e61269.JPG',5,'ed48ca0058018d723763c76db7980cf981b9f843670703b8c9a85fa2e6ad5cac'),('7ee3600ac2f353b0e73cedf6b680b53a2034519f611cb164040fd438b7651a6b','경기 성남시 분당구 판교역로 4',0,'2022-02-17 16:51:16',37.394875,127.110414,'추워','http://i6c102.p.ssafy.io/images/profile/4c491090-09ae-48a8-8a99-9150f8b85030.jpg',0,'0bd5a69df78a9f9daf4b69eae7eeb64fbb46643a6a827c56dc4ab7a20e44447f'),('9c1d0ceb53672fb43ae728246b7a151628ffa208b653096e6afdebb994da8967','경기 성남시 분당구 판교역로 235',0,'2022-02-17 18:43:37',37.4020589,127.1086288,'영하',NULL,0,'8e63d3fb5cc589ba7ddc9e428fdf63abec815fc30da83582c3438c7ccc23c720'),('c945b7cd33248e4ef03b936ac3ca3854425cc7376d3be59d9fb6d6cdd6e93b4b','경기 성남시 분당구 판교역로 4',0,'2022-02-17 17:03:54',37.394875,127.110414,'123',NULL,0,'9c3b478d98b080ba7673bb89c457980b9a6179f24add76d0572e591660cdab48'),('e6411e18f543766420a6a2b354c2834d1ea613f63187fa51419b675f28a32da7','충남 아산시 음봉면 원남리 248-1',0,'2022-02-17 17:01:33',36.8647831,127.0265074,'응애김현수',NULL,0,'0477f0d12a8c2e2d7721c671affc283ddd44359425bd0c74451b438447573eb1'),('e8c1626d2306245e360c9edd054442804ab428ae2f326f7e3ce5e947a26544b9','경기 성남시 분당구 판교역로 235',0,'2022-02-17 17:52:08',37.4020589,127.1086288,'박상우',NULL,0,'1577d797d74a5e377f8a65a740da4866165a39d841af5ca82051d6080fa38ac4');
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zzim`
--

DROP TABLE IF EXISTS `zzim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zzim` (
  `article_article_id` bigint(20) NOT NULL,
  `user_profile_profile_id` varchar(255) NOT NULL,
  PRIMARY KEY (`article_article_id`,`user_profile_profile_id`),
  KEY `FKc1f0o98xxof8rdyx456r9k2is` (`user_profile_profile_id`),
  CONSTRAINT `FKc1f0o98xxof8rdyx456r9k2is` FOREIGN KEY (`user_profile_profile_id`) REFERENCES `user_profile` (`profile_id`),
  CONSTRAINT `FKtehwcapqbpujp08s3yrd8f72p` FOREIGN KEY (`article_article_id`) REFERENCES `article` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zzim`
--

LOCK TABLES `zzim` WRITE;
/*!40000 ALTER TABLE `zzim` DISABLE KEYS */;
INSERT INTO `zzim` VALUES (6,'c945b7cd33248e4ef03b936ac3ca3854425cc7376d3be59d9fb6d6cdd6e93b4b'),(8,'621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81'),(10,'621d53161bef0d7e8b8d51c0d8d8bf2325fdefad8454b15c917494ce7306ec81');
/*!40000 ALTER TABLE `zzim` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-17 18:44:16
