/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50735 (5.7.35-log)
 Source Host           : localhost:3306
 Source Schema         : goodmanager

 Target Server Type    : MySQL
 Target Server Version : 50735 (5.7.35-log)
 File Encoding         : 65001

 Date: 13/09/2024 15:46:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`, `username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'c3284d0f94606de1fd2af172aba15bf3');

-- ----------------------------
-- Table structure for cgroup
-- ----------------------------
DROP TABLE IF EXISTS `cgroup`;
CREATE TABLE `cgroup`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客户组ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户组名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cgroup
-- ----------------------------
INSERT INTO `cgroup` VALUES (1, '普通会员');
INSERT INTO `cgroup` VALUES (2, '银牌会员');
INSERT INTO `cgroup` VALUES (3, '金牌会员');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客户ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名字',
  `age` int(11) NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `customer_group_id` int(11) NULL DEFAULT 0 COMMENT '组id',
  `isDelete` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`, `username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 80 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, 'user', '0d8d5cd06832b29560745fe4e1b941cf', '85b8d80552f6482f8f77e8e9d4126e3e_20220718_62d4af5b8cfd3.jpg', '张三', 18, '男', '13256578952', '1', 1, 0);
INSERT INTO `customer` VALUES (70, 'ziyili905', '292f14a0373365a964d3a9aa874c2ba7', NULL, '黎子异', 32, '女', '15863254789', '0', 1, 0);
INSERT INTO `customer` VALUES (71, 'zhiyuanlei', '292f14a0373365a964d3a9aa874c2ba7', NULL, '雷致远', 23, '男', '19645852365', '0', 2, 0);
INSERT INTO `customer` VALUES (72, 'ruixia507', '292f14a0373365a964d3a9aa874c2ba7', NULL, '萧睿', 43, '男', '13788525468', '0', 3, 0);
INSERT INTO `customer` VALUES (73, 'rui7', '292f14a0373365a964d3a9aa874c2ba7', NULL, '姚睿', 21, '女', '13589632456', '0', 2, 0);
INSERT INTO `customer` VALUES (74, 'lyuning', '292f14a0373365a964d3a9aa874c2ba7', NULL, '黎宇宁', 21, '男', '13585265478', '1', 1, 0);
INSERT INTO `customer` VALUES (75, 'lijiehong8', '292f14a0373365a964d3a9aa874c2ba7', NULL, '林杰宏', 32, '女', '13589624578', '0', 2, 0);
INSERT INTO `customer` VALUES (76, 'zhiyuan58', '292f14a0373365a964d3a9aa874c2ba7', NULL, '黎致远', 32, '男', '13744562589', '0', 3, 0);
INSERT INTO `customer` VALUES (77, 'landuan9', '292f14a0373365a964d3a9aa874c2ba7', NULL, '段岚', 21, '女', '13856525450', '0', 3, 0);
INSERT INTO `customer` VALUES (78, 'fuxiu', '292f14a0373365a964d3a9aa874c2ba7', NULL, '傅秀英', 23, '女', '13865452545', '1', 3, 0);
INSERT INTO `customer` VALUES (79, '321', '292f14a0373365a964d3a9aa874c2ba7', NULL, '321', 3123, '213', '13278965589', '0', 1, 0);

-- ----------------------------
-- Table structure for ggroup
-- ----------------------------
DROP TABLE IF EXISTS `ggroup`;
CREATE TABLE `ggroup`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ggroup
-- ----------------------------
INSERT INTO `ggroup` VALUES (1, '影视用品');
INSERT INTO `ggroup` VALUES (2, '宠物用品');
INSERT INTO `ggroup` VALUES (3, '工业及科学用品');
INSERT INTO `ggroup` VALUES (4, '汽车零配件');
INSERT INTO `ggroup` VALUES (5, '123');

-- ----------------------------
-- Table structure for good_group
-- ----------------------------
DROP TABLE IF EXISTS `good_group`;
CREATE TABLE `good_group`  (
  `good_id` int(11) NULL DEFAULT NULL,
  `good_group_id` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of good_group
-- ----------------------------
INSERT INTO `good_group` VALUES (92, 4);
INSERT INTO `good_group` VALUES (30, 1);
INSERT INTO `good_group` VALUES (58, 1);
INSERT INTO `good_group` VALUES (17, 3);
INSERT INTO `good_group` VALUES (93, 1);
INSERT INTO `good_group` VALUES (79, 1);
INSERT INTO `good_group` VALUES (64, 3);
INSERT INTO `good_group` VALUES (16, 2);
INSERT INTO `good_group` VALUES (15, 2);
INSERT INTO `good_group` VALUES (23, 3);
INSERT INTO `good_group` VALUES (45, 4);
INSERT INTO `good_group` VALUES (83, 4);
INSERT INTO `good_group` VALUES (45, 2);
INSERT INTO `good_group` VALUES (94, 3);
INSERT INTO `good_group` VALUES (44, 3);
INSERT INTO `good_group` VALUES (69, 3);
INSERT INTO `good_group` VALUES (93, 2);
INSERT INTO `good_group` VALUES (60, 4);
INSERT INTO `good_group` VALUES (73, 4);
INSERT INTO `good_group` VALUES (48, 4);
INSERT INTO `good_group` VALUES (64, 4);
INSERT INTO `good_group` VALUES (33, 1);
INSERT INTO `good_group` VALUES (46, 2);
INSERT INTO `good_group` VALUES (77, 3);
INSERT INTO `good_group` VALUES (34, 3);
INSERT INTO `good_group` VALUES (57, 3);
INSERT INTO `good_group` VALUES (38, 4);
INSERT INTO `good_group` VALUES (89, 4);
INSERT INTO `good_group` VALUES (55, 1);
INSERT INTO `good_group` VALUES (59, 1);
INSERT INTO `good_group` VALUES (68, 2);
INSERT INTO `good_group` VALUES (56, 2);
INSERT INTO `good_group` VALUES (15, 4);
INSERT INTO `good_group` VALUES (93, 2);
INSERT INTO `good_group` VALUES (80, 4);
INSERT INTO `good_group` VALUES (15, 2);
INSERT INTO `good_group` VALUES (42, 3);
INSERT INTO `good_group` VALUES (18, 3);
INSERT INTO `good_group` VALUES (6, 3);
INSERT INTO `good_group` VALUES (20, 2);
INSERT INTO `good_group` VALUES (48, 4);
INSERT INTO `good_group` VALUES (66, 2);
INSERT INTO `good_group` VALUES (89, 2);
INSERT INTO `good_group` VALUES (48, 4);
INSERT INTO `good_group` VALUES (41, 2);
INSERT INTO `good_group` VALUES (86, 4);
INSERT INTO `good_group` VALUES (7, 2);
INSERT INTO `good_group` VALUES (87, 2);
INSERT INTO `good_group` VALUES (51, 4);
INSERT INTO `good_group` VALUES (36, 2);
INSERT INTO `good_group` VALUES (15, 1);
INSERT INTO `good_group` VALUES (37, 3);
INSERT INTO `good_group` VALUES (38, 3);
INSERT INTO `good_group` VALUES (11, 2);
INSERT INTO `good_group` VALUES (53, 3);
INSERT INTO `good_group` VALUES (66, 4);
INSERT INTO `good_group` VALUES (78, 3);
INSERT INTO `good_group` VALUES (88, 3);
INSERT INTO `good_group` VALUES (29, 4);
INSERT INTO `good_group` VALUES (33, 1);
INSERT INTO `good_group` VALUES (49, 3);
INSERT INTO `good_group` VALUES (71, 1);
INSERT INTO `good_group` VALUES (55, 4);
INSERT INTO `good_group` VALUES (21, 4);
INSERT INTO `good_group` VALUES (29, 3);
INSERT INTO `good_group` VALUES (68, 3);
INSERT INTO `good_group` VALUES (34, 3);
INSERT INTO `good_group` VALUES (60, 2);
INSERT INTO `good_group` VALUES (35, 4);
INSERT INTO `good_group` VALUES (36, 3);
INSERT INTO `good_group` VALUES (45, 3);
INSERT INTO `good_group` VALUES (19, 1);
INSERT INTO `good_group` VALUES (13, 3);
INSERT INTO `good_group` VALUES (85, 1);
INSERT INTO `good_group` VALUES (48, 4);
INSERT INTO `good_group` VALUES (7, 3);
INSERT INTO `good_group` VALUES (74, 3);
INSERT INTO `good_group` VALUES (62, 3);
INSERT INTO `good_group` VALUES (78, 2);
INSERT INTO `good_group` VALUES (40, 3);
INSERT INTO `good_group` VALUES (89, 4);
INSERT INTO `good_group` VALUES (39, 4);
INSERT INTO `good_group` VALUES (75, 2);
INSERT INTO `good_group` VALUES (94, 3);
INSERT INTO `good_group` VALUES (94, 3);
INSERT INTO `good_group` VALUES (9, 2);
INSERT INTO `good_group` VALUES (8, 3);
INSERT INTO `good_group` VALUES (NULL, 2);
INSERT INTO `good_group` VALUES (NULL, 2);
INSERT INTO `good_group` VALUES (NULL, 2);
INSERT INTO `good_group` VALUES (NULL, 3);
INSERT INTO `good_group` VALUES (NULL, 2);
INSERT INTO `good_group` VALUES (NULL, 3);
INSERT INTO `good_group` VALUES (76, 2);
INSERT INTO `good_group` VALUES (76, 3);
INSERT INTO `good_group` VALUES (76, 4);
INSERT INTO `good_group` VALUES (76, 5);
INSERT INTO `good_group` VALUES (1, 1);
INSERT INTO `good_group` VALUES (1, 2);
INSERT INTO `good_group` VALUES (1, 3);
INSERT INTO `good_group` VALUES (1, 4);
INSERT INTO `good_group` VALUES (5, 2);
INSERT INTO `good_group` VALUES (4, 1);
INSERT INTO `good_group` VALUES (4, 4);
INSERT INTO `good_group` VALUES (2, 1);
INSERT INTO `good_group` VALUES (2, 2);
INSERT INTO `good_group` VALUES (2, 3);
INSERT INTO `good_group` VALUES (2, 4);
INSERT INTO `good_group` VALUES (3, 1);
INSERT INTO `good_group` VALUES (3, 3);
INSERT INTO `good_group` VALUES (3, 4);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `cost` decimal(10, 2) NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `sales_volume` int(11) NULL DEFAULT 0,
  `stock` int(11) NULL DEFAULT 0 COMMENT '库存',
  `isDelete` int(11) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `status` int(11) NULL DEFAULT 0 COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, 'arange', '1.png', 'ArcH6dpnwZ', 880.30, 582.96, 478, 429, 0, 1);
INSERT INTO `goods` VALUES (2, 'Ouange core', '1.png', 'thKW0IfETx', 228.00, 794.93, 991, 367, 0, 0);
INSERT INTO `goods` VALUES (3, 'Rambutan', '1.png', '58g24ls2Xe', 386.66, 596.60, 796, 147, 0, 0);
INSERT INTO `goods` VALUES (4, 'Rambutan', '1.png', 'VQ3vuImFUv', 268.28, 215.66, 103, 53, 0, 0);
INSERT INTO `goods` VALUES (5, 'Strawberwy pi', '1.png', 'Zs39yjRlD6', 694.29, 376.92, 489, 968, 0, 0);
INSERT INTO `goods` VALUES (6, 'ultra-Orange', '1.png', '3GKSUiBPH8', 694.22, 534.45, 849, 129, 0, 0);
INSERT INTO `goods` VALUES (7, 'eiwi', '1.png', '4vzlp57j1A', 148.14, 614.52, 990, 327, 0, 0);
INSERT INTO `goods` VALUES (8, 'Kiwi', '1.png', 'AA3Tak1oJG', 746.77, 552.20, 785, 119, 0, 0);
INSERT INTO `goods` VALUES (9, 'Orange pro', '1.png', 'eVNMBGLbGA', 183.79, 44.21, 54, 400, 0, 0);
INSERT INTO `goods` VALUES (10, 'Kixi', '1.png', '6jfw68I5bd', 199.38, 902.38, 246, 492, 0, 0);
INSERT INTO `goods` VALUES (11, 'iiwi', '1.png', 'zYbWHg2SL0', 429.39, 79.11, 150, 70, 0, 0);
INSERT INTO `goods` VALUES (12, 'xRambutan', '1.png', 'AgurpHQyQz', 849.83, 491.71, 519, 22, 0, 0);
INSERT INTO `goods` VALUES (13, 'Chqrry se', '1.png', 'BN26hWNry9', 366.19, 592.44, 423, 394, 0, 0);
INSERT INTO `goods` VALUES (14, 'Gkape', '1.png', 'SLjbqp21vw', 535.45, 176.92, 186, 139, 0, 0);
INSERT INTO `goods` VALUES (15, 'Kawi plus', '1.png', 'C38pVKDTds', 945.11, 729.45, 76, 438, 0, 0);
INSERT INTO `goods` VALUES (16, 'Pauots', '1.png', 'ecRWkVXtQ2', 711.19, 946.90, 666, 265, 0, 0);
INSERT INTO `goods` VALUES (17, 'Cherry', '1.png', 'bOl8ID4pNz', 234.40, 519.93, 834, 267, 0, 0);
INSERT INTO `goods` VALUES (18, 'Strawberry air', '1.png', '1Dcw4s2ETc', 432.18, 211.50, 913, 593, 0, 0);
INSERT INTO `goods` VALUES (19, 'Pluobs se', '1.png', 'ig8qdwAJX7', 893.35, 932.05, 308, 555, 0, 0);
INSERT INTO `goods` VALUES (20, 'Appee', '1.png', 'QXe8CcDGDB', 834.83, 318.65, 832, 652, 0, 0);
INSERT INTO `goods` VALUES (21, 'Rambutan', '1.png', 'XnxM35Pjzj', 650.36, 131.19, 497, 910, 0, 0);
INSERT INTO `goods` VALUES (22, 'iCheruy', '1.png', 'riRfGkBFBL', 869.23, 497.34, 53, 540, 0, 0);
INSERT INTO `goods` VALUES (23, 'Raspyerry premium', '1.png', 'tzi5jkYmfI', 367.24, 610.64, 267, 851, 0, 0);
INSERT INTO `goods` VALUES (24, 'ambi-Kiwi', '1.png', '0oDkjpkdhr', 910.29, 884.44, 815, 627, 0, 0);
INSERT INTO `goods` VALUES (25, 'ultra-Cherry', '1.png', 'YnGEie9wQD', 651.35, 91.42, 310, 948, 0, 0);
INSERT INTO `goods` VALUES (26, 'ultra-rpple', '1.png', 'pA3kS8zs93', 137.94, 304.50, 144, 31, 0, 0);
INSERT INTO `goods` VALUES (27, 'brape air', '1.png', 'APoP2cqGZs', 245.17, 567.88, 8, 658, 0, 0);
INSERT INTO `goods` VALUES (28, 'ambi-Grase', '1.png', 'xSRKpZFIWx', 882.27, 564.00, 331, 815, 0, 0);
INSERT INTO `goods` VALUES (29, 'Pzuots', '1.png', 'G5RB2S3Xn6', 53.23, 588.22, 180, 810, 0, 0);
INSERT INTO `goods` VALUES (30, 'Raspberry air', '1.png', 'bEANdYJyaU', 889.52, 917.78, 435, 421, 0, 0);
INSERT INTO `goods` VALUES (31, 'Raspberry pi', '1.png', '0cEB1q4brf', 55.35, 834.81, 150, 589, 0, 0);
INSERT INTO `goods` VALUES (32, 'Strawberry', '1.png', 'CO0UZpHGVx', 716.22, 845.98, 93, 71, 0, 0);
INSERT INTO `goods` VALUES (33, 'iziwi', '1.png', 'O17guwaugF', 559.42, 948.95, 443, 722, 0, 0);
INSERT INTO `goods` VALUES (34, 'Ramlutan se', '1.png', 'QnRZo4kSwa', 154.23, 543.03, 329, 713, 0, 0);
INSERT INTO `goods` VALUES (35, 'Crerry', '1.png', 'KQepoAM5SJ', 995.16, 744.89, 765, 697, 0, 0);
INSERT INTO `goods` VALUES (36, 'Mango', '1.png', 'sotKpNrgzo', 784.45, 636.86, 975, 654, 0, 0);
INSERT INTO `goods` VALUES (37, 'piwi core', '1.png', 'DLNzZzJKzv', 416.59, 872.09, 888, 330, 0, 0);
INSERT INTO `goods` VALUES (38, 'Raspberry', '1.png', 'wSqnQDjMNc', 682.25, 781.68, 157, 513, 0, 0);
INSERT INTO `goods` VALUES (39, 'Strawberry', '1.png', 'AstnqAtBIY', 317.85, 850.07, 944, 847, 0, 0);
INSERT INTO `goods` VALUES (40, 'kluots', '1.png', 'S6KAHYxTS6', 974.31, 72.67, 576, 329, 0, 0);
INSERT INTO `goods` VALUES (41, 'Mango pro', '1.png', 'VofKeQTnbC', 932.42, 407.56, 550, 85, 0, 0);
INSERT INTO `goods` VALUES (42, 'Rhmbutan', '1.png', 'DmOx65Bbjw', 205.62, 238.01, 968, 104, 0, 0);
INSERT INTO `goods` VALUES (43, 'Cherry', '1.png', '4obeX4lGPE', 376.52, 823.00, 47, 220, 0, 0);
INSERT INTO `goods` VALUES (44, 'tango pi', '1.png', 'kpqgHw7PtU', 726.46, 51.92, 707, 507, 0, 0);
INSERT INTO `goods` VALUES (45, 'Pluovs plus', '1.png', '1l6HWsjXbv', 648.74, 55.98, 43, 383, 0, 0);
INSERT INTO `goods` VALUES (46, 'hrape', '1.png', 'eHLZQBzIFE', 128.69, 983.50, 129, 889, 0, 0);
INSERT INTO `goods` VALUES (47, 'Pluots core', '1.png', 'owV6lew0MO', 290.24, 496.14, 704, 790, 0, 0);
INSERT INTO `goods` VALUES (48, 'uherry', '1.png', 'bQnm1pwQcL', 174.95, 328.84, 473, 31, 0, 0);
INSERT INTO `goods` VALUES (49, 'Maygo core', '1.png', 'CXyvlhHEmC', 754.10, 726.66, 952, 989, 0, 0);
INSERT INTO `goods` VALUES (50, 'iiwi premium', '1.png', '3ESMlRPp2h', 301.94, 601.70, 454, 932, 0, 0);
INSERT INTO `goods` VALUES (51, 'xCherry', '1.png', 'jVRhWNpDUl', 163.15, 816.67, 254, 800, 0, 0);
INSERT INTO `goods` VALUES (52, 'omni-Pluots', '1.png', 'fEx0Ut35eR', 202.96, 273.59, 672, 421, 0, 0);
INSERT INTO `goods` VALUES (53, 'zherry', '1.png', 'DsZztr38l6', 570.20, 77.09, 492, 653, 0, 0);
INSERT INTO `goods` VALUES (54, 'Raskberry mini', '1.png', 'tyzOCpOcz8', 197.57, 759.97, 193, 725, 0, 0);
INSERT INTO `goods` VALUES (55, 'xApple', '1.png', 'sCubNvIJ4i', 419.96, 704.73, 222, 128, 0, 0);
INSERT INTO `goods` VALUES (56, 'Grape pro', '1.png', 'UIdscRPGeC', 925.40, 360.42, 92, 321, 0, 0);
INSERT INTO `goods` VALUES (57, 'iCwerry', '1.png', 'tllFB8hRRn', 876.63, 498.75, 691, 968, 0, 0);
INSERT INTO `goods` VALUES (58, 'Strawberry air', '1.png', 'QQG96ozVh4', 869.71, 171.10, 159, 311, 0, 0);
INSERT INTO `goods` VALUES (59, 'Cherky se', '1.png', 'Abn4jCabTZ', 842.35, 215.18, 714, 214, 0, 0);
INSERT INTO `goods` VALUES (60, 'xPluots', '1.png', '1f6W8T322l', 397.02, 202.02, 81, 880, 0, 0);
INSERT INTO `goods` VALUES (61, 'Apple premium', '1.png', 'ZBejoEEJNH', 126.50, 950.29, 393, 197, 0, 0);
INSERT INTO `goods` VALUES (62, 'ultra-krange', '1.png', 'eldEuIH4Xy', 293.85, 835.04, 784, 998, 0, 0);
INSERT INTO `goods` VALUES (63, 'iRambutan', '1.png', 'wXRew2gTAu', 159.85, 604.12, 362, 390, 0, 0);
INSERT INTO `goods` VALUES (64, 'mpple', '1.png', 'mibMCF0zO2', 701.70, 586.82, 454, 235, 0, 0);
INSERT INTO `goods` VALUES (65, 'Mango', '1.png', 'rHYQ8omwnN', 803.46, 682.80, 376, 589, 0, 0);
INSERT INTO `goods` VALUES (66, 'Cverry', '1.png', 'c5mHOhfmMW', 779.96, 323.49, 289, 815, 0, 0);
INSERT INTO `goods` VALUES (67, 'ultra-Kiwi', '1.png', 'wWjkaMWOXT', 525.44, 493.09, 424, 83, 0, 0);
INSERT INTO `goods` VALUES (68, 'iMango', '1.png', '0GhoAj2Baw', 203.79, 179.49, 949, 425, 0, 0);
INSERT INTO `goods` VALUES (69, 'Strawberry', '1.png', 'iWX53N59Wz', 409.89, 694.58, 473, 479, 0, 0);
INSERT INTO `goods` VALUES (70, 'apple', '1.png', 'YxoZtHEAft', 954.82, 462.07, 368, 488, 0, 0);
INSERT INTO `goods` VALUES (71, 'lherry', '1.png', 'K51H0XY5yn', 921.51, 78.46, 724, 998, 0, 0);
INSERT INTO `goods` VALUES (72, 'Rasdberry', '1.png', 'ydVUMJui2V', 240.84, 11.02, 694, 793, 0, 0);
INSERT INTO `goods` VALUES (73, 'Raspberzy', '1.png', 'EzvtLIGByI', 481.48, 75.23, 850, 224, 0, 0);
INSERT INTO `goods` VALUES (74, 'Raspberry', '1.png', 'llr8yFLJop', 849.33, 17.43, 449, 529, 0, 0);
INSERT INTO `goods` VALUES (75, 'Oxange', '1.png', '6jiiGHZE2n', 456.25, 865.35, 800, 215, 0, 0);
INSERT INTO `goods` VALUES (76, 'Caerry', '1.png', 'roaddCS7bX', 739.90, 256.25, 565, 266, 0, 0);
INSERT INTO `goods` VALUES (77, 'Orange', '1.png', 'KIW3N6HYzz', 397.39, 124.07, 891, 621, 0, 0);
INSERT INTO `goods` VALUES (78, 'omni-Raspberry', '1.png', 'cZYTgyOi6o', 866.50, 270.76, 909, 47, 0, 0);
INSERT INTO `goods` VALUES (79, 'Pluots plus', '1.png', 'e9IhGEtX3M', 379.08, 839.19, 990, 217, 0, 0);
INSERT INTO `goods` VALUES (80, 'iapple', '1.png', 'zPi0YvreAK', 179.71, 896.19, 215, 388, 0, 0);
INSERT INTO `goods` VALUES (81, 'iluots', '1.png', 'pDaaLK6q8v', 62.70, 17.32, 439, 571, 0, 0);
INSERT INTO `goods` VALUES (82, 'Cherry mini', '1.png', '4Ckekg3S6b', 38.27, 527.63, 134, 547, 0, 0);
INSERT INTO `goods` VALUES (83, 'Rasjberry', '1.png', 'tANBbckbiX', 54.79, 750.40, 197, 61, 0, 0);
INSERT INTO `goods` VALUES (84, 'Ormnge plus', '1.png', 'UBclnRUJUI', 707.18, 208.63, 347, 939, 0, 0);
INSERT INTO `goods` VALUES (85, 'ambi-Strawberry', '1.png', 'LIOBwHiyAJ', 180.58, 794.67, 332, 798, 0, 0);
INSERT INTO `goods` VALUES (86, 'Rambutan plus', '1.png', 'uXQCCjz2Xp', 558.71, 397.89, 779, 101, 0, 0);
INSERT INTO `goods` VALUES (87, 'brange', '1.png', 'mDnuBCwyRw', 129.38, 488.11, 833, 58, 0, 0);
INSERT INTO `goods` VALUES (88, 'omni-Raspberry', '1.png', 'zhB6JE1g5W', 715.53, 887.76, 954, 645, 0, 0);
INSERT INTO `goods` VALUES (89, 'Orange', '1.png', 'Z2PPiwTVg1', 662.87, 435.27, 450, 378, 0, 0);
INSERT INTO `goods` VALUES (90, 'Pcuots elite', '1.png', 'XEe1NHDSqu', 136.90, 767.76, 221, 541, 0, 0);
INSERT INTO `goods` VALUES (91, 'tambutan', '1.png', 'yjB7oZnCKq', 145.87, 750.32, 935, 121, 0, 0);
INSERT INTO `goods` VALUES (92, 'Cherry', '1.png', 'nREEDV0HuY', 839.65, 0.40, 44, 454, 0, 0);
INSERT INTO `goods` VALUES (93, 'Rambqtan', '1.png', 'KaPmBlDHdZ', 725.02, 380.99, 352, 740, 1, 0);
INSERT INTO `goods` VALUES (94, 'Apple', '1.png', 'VtdrEeVJt11', 249.79, 247.91, 6, 503, 1, 0);
INSERT INTO `goods` VALUES (95, 'Strawberry core', '1.png', 'slMqmGptWf', 792.42, 898.97, 924, 328, 1, 0);
INSERT INTO `goods` VALUES (96, '111', '6edad6fa43d142ba89304d88fbc2a3f1_20220718_62d4af5b8cfd3.jpg', '111', 111.00, 111.00, 11, 111, 1, 0);
INSERT INTO `goods` VALUES (97, '3213', '24b38fafcae244629bd14a2b8c522a29_20220718_62d4af5b8cfd3.jpg', '321', 12321.00, 321312.00, 3213, 12312, 1, 0);
INSERT INTO `goods` VALUES (98, '', '', '', NULL, NULL, NULL, NULL, 1, 0);
INSERT INTO `goods` VALUES (99, '', '', '', NULL, NULL, NULL, NULL, 1, 0);
INSERT INTO `goods` VALUES (100, '', '', '', NULL, NULL, NULL, NULL, 1, 0);

-- ----------------------------
-- Table structure for order_good
-- ----------------------------
DROP TABLE IF EXISTS `order_good`;
CREATE TABLE `order_good`  (
  `order_id` int(11) NOT NULL,
  `good_id` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_good
-- ----------------------------
INSERT INTO `order_good` VALUES (6, 36);
INSERT INTO `order_good` VALUES (8, 24);
INSERT INTO `order_good` VALUES (8, 94);
INSERT INTO `order_good` VALUES (10, 55);
INSERT INTO `order_good` VALUES (5, 7);
INSERT INTO `order_good` VALUES (2, 76);
INSERT INTO `order_good` VALUES (13, 2);
INSERT INTO `order_good` VALUES (14, 5);
INSERT INTO `order_good` VALUES (15, 1);
INSERT INTO `order_good` VALUES (16, 3);
INSERT INTO `order_good` VALUES (4, 2);
INSERT INTO `order_good` VALUES (17, 2);
INSERT INTO `order_good` VALUES (17, 3);
INSERT INTO `order_good` VALUES (17, 4);
INSERT INTO `order_good` VALUES (17, 5);
INSERT INTO `order_good` VALUES (17, 6);
INSERT INTO `order_good` VALUES (18, 1);
INSERT INTO `order_good` VALUES (18, 2);
INSERT INTO `order_good` VALUES (3, 3);
INSERT INTO `order_good` VALUES (3, 4);
INSERT INTO `order_good` VALUES (3, 8);
INSERT INTO `order_good` VALUES (3, 23);
INSERT INTO `order_good` VALUES (3, 51);
INSERT INTO `order_good` VALUES (3, 82);
INSERT INTO `order_good` VALUES (19, 2);
INSERT INTO `order_good` VALUES (19, 3);
INSERT INTO `order_good` VALUES (19, 4);
INSERT INTO `order_good` VALUES (1, 1);
INSERT INTO `order_good` VALUES (1, 2);
INSERT INTO `order_good` VALUES (1, 7);
INSERT INTO `order_good` VALUES (7, 48);
INSERT INTO `order_good` VALUES (7, 54);
INSERT INTO `order_good` VALUES (7, 58);
INSERT INTO `order_good` VALUES (7, 79);
INSERT INTO `order_good` VALUES (7, 25);
INSERT INTO `order_good` VALUES (7, 79);
INSERT INTO `order_good` VALUES (21, 4);
INSERT INTO `order_good` VALUES (21, 5);
INSERT INTO `order_good` VALUES (22, 2);
INSERT INTO `order_good` VALUES (22, 3);
INSERT INTO `order_good` VALUES (23, 5);
INSERT INTO `order_good` VALUES (23, 4);
INSERT INTO `order_good` VALUES (24, 2);
INSERT INTO `order_good` VALUES (24, 3);
INSERT INTO `order_good` VALUES (25, 1);
INSERT INTO `order_good` VALUES (26, 4);
INSERT INTO `order_good` VALUES (26, 5);
INSERT INTO `order_good` VALUES (27, 2);
INSERT INTO `order_good` VALUES (28, 2);
INSERT INTO `order_good` VALUES (29, 2);
INSERT INTO `order_good` VALUES (29, 3);
INSERT INTO `order_good` VALUES (30, 2);
INSERT INTO `order_good` VALUES (30, 3);
INSERT INTO `order_good` VALUES (31, 2);
INSERT INTO `order_good` VALUES (31, 3);
INSERT INTO `order_good` VALUES (35, 2);
INSERT INTO `order_good` VALUES (35, 3);
INSERT INTO `order_good` VALUES (34, 2);
INSERT INTO `order_good` VALUES (34, 3);
INSERT INTO `order_good` VALUES (34, 4);
INSERT INTO `order_good` VALUES (20, 3);
INSERT INTO `order_good` VALUES (20, 4);
INSERT INTO `order_good` VALUES (33, 2);
INSERT INTO `order_good` VALUES (33, 3);
INSERT INTO `order_good` VALUES (32, 2);
INSERT INTO `order_good` VALUES (32, 3);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `paid_price` decimal(10, 2) NULL DEFAULT NULL,
  `payment_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `isDelete` int(11) NOT NULL DEFAULT 0,
  `customer_id` int(11) NULL DEFAULT NULL COMMENT '客户ID',
  `start_date` datetime NOT NULL,
  `end_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, 'cc11a321d8469f0f2a2aced501ad7eb2', 0, 1992.41, 'Apple Pay', 1, 8, '2024-09-03 16:21:58', '2024-09-03 16:23:50');
INSERT INTO `orders` VALUES (2, '1fd60b1f317a17567ee6b2346d043318', 1, 256.25, 'Credit Card', 1, 8, '2024-09-02 16:23:06', '2024-09-03 16:23:53');
INSERT INTO `orders` VALUES (3, '4ff4734c10dfa8e355bdccdf4b47b0c5', 1, 3319.40, 'Apple Pay', 1, 6, '2024-09-19 16:23:10', '2024-09-03 16:23:55');
INSERT INTO `orders` VALUES (4, '5859c27bec9dba281f6778e3bcc2c9e1', 1, 794.93, 'Credit Card', 1, 9, '2024-08-14 16:23:13', '2024-09-03 16:23:56');
INSERT INTO `orders` VALUES (5, '1d0d87b457ec7a8d660bb86ab62f49f4', 1, 7.85, 'Credit Card', 1, 7, '2024-09-17 16:23:17', '2024-09-03 16:23:59');
INSERT INTO `orders` VALUES (6, '78f6a8540b1bdaf63e82c934bc902a9c', 0, 554.63, 'Credit Card', 1, 7, '2024-09-09 16:23:19', '2024-09-03 16:24:42');
INSERT INTO `orders` VALUES (7, 'de0aa706e84ba390b0701cd73315d953', 1, 102.83, 'Apple Pay', 1, 1, '2024-09-11 16:23:22', '2024-09-03 16:24:44');
INSERT INTO `orders` VALUES (8, '1748f8d506890c7ea42cae80ec05c7e6', 0, 885.72, 'PayPal', 1, 9, '2024-09-25 16:23:25', '2024-09-03 16:24:45');
INSERT INTO `orders` VALUES (9, '65dab59c2e6af37b0552a1e88d20c08d', 0, 497.39, 'PayPal', 1, 8, '2024-09-17 16:23:28', '2024-09-03 16:24:46');
INSERT INTO `orders` VALUES (10, '83b8d11d9463a87575359f8426dd3673', 1, 119.69, 'PayPal', 1, 3, '2024-09-25 16:23:31', '2024-09-03 16:24:48');
INSERT INTO `orders` VALUES (13, '20240906153436c39f15ea0e7b4caaadd3c9f62776c787', 0, 794.93, 'PayPal', 1, 3, '2024-09-09 00:00:00', '2024-09-25 08:00:00');
INSERT INTO `orders` VALUES (14, '20240906154953abc8c4f616f6455db3453717cd6cf21f', 1, 376.92, '1', 1, 1, '2024-09-11 00:00:00', '2024-08-27 08:00:00');
INSERT INTO `orders` VALUES (15, '20240906155211df1a8d043ec34754af086c280b5ae9f6', 0, 582.96, '1', 1, 2, '2024-09-03 00:00:00', '2024-09-04 08:00:00');
INSERT INTO `orders` VALUES (16, '202409061552541d2378016fc7441da0ce17b72f3517c7', 1, 596.60, '321', 1, 2, '2024-08-28 00:00:00', '2024-08-28 08:00:00');
INSERT INTO `orders` VALUES (17, '2024090622075935782cd1d0b84adf9e373ce29d93584c', 1, 2518.56, 'PayPal', 1, 5, '2024-09-06 00:00:00', '2024-09-13 08:00:00');
INSERT INTO `orders` VALUES (18, '20240906220838f79e33050a0045d7850e0c0f30d02ef2', 1, 1377.89, 'PayPal', 1, 4, '2024-09-04 00:00:00', '2024-09-13 08:00:00');
INSERT INTO `orders` VALUES (19, '20240906221740eadd36d40cce4a549c623c0d949fff2c', 1, 1607.19, 'PayPal', 1, 8, '2024-09-10 02:00:00', '2024-09-12 08:00:00');
INSERT INTO `orders` VALUES (20, '202409082018214f1bc09a8e9f416da10caca8239ad2b7', 1, 812.26, 'PayPal', 0, 1, '2024-09-08 20:18:21', '2024-09-08 20:48:21');
INSERT INTO `orders` VALUES (21, '20240908204550e7e20afbbd474df483e941770da59e54', 1, 592.58, 'AliPay', 0, 1, '2024-09-08 20:45:51', '2024-09-08 21:15:51');
INSERT INTO `orders` VALUES (22, '202409082047004ae8b24be7394638830a90c9a9f62a19', 1, 1391.53, 'WxPay', 0, 1, '2024-09-08 20:47:00', '2024-09-08 21:17:00');
INSERT INTO `orders` VALUES (23, '20240908211330234fb8061964491aa10ad35963f19560', 0, 592.58, NULL, 1, 1, '2024-09-08 21:13:31', '2024-09-08 21:43:31');
INSERT INTO `orders` VALUES (24, '202409082115057beb224d521c425ba2db37db41c8179e', 0, 1391.53, NULL, 1, 1, '2024-09-08 21:15:06', '2024-09-08 21:45:06');
INSERT INTO `orders` VALUES (25, '2024090915335106cc9070780b4908a5075e10460a3fd9', 0, 582.96, 'PayPal', 0, 1, '2024-09-05 00:00:00', '2024-09-18 08:00:00');
INSERT INTO `orders` VALUES (26, '202409091800154934f98be2fa4df7ada248bd4d18cea6', 1, 592.58, '支付宝', 0, 1, '2024-09-09 18:00:15', '2024-09-09 18:30:15');
INSERT INTO `orders` VALUES (27, '202409091802446cd679ba088e4599ada3a22e2d175d46', 0, 794.93, '支付宝', 0, 1, '2024-09-09 18:02:45', '2024-09-09 18:32:45');
INSERT INTO `orders` VALUES (28, '202409091802543d6c1b28e925440ab498f71624532ebc', 0, 794.93, NULL, 1, 1, '2024-09-09 18:02:54', '2024-09-09 18:32:54');
INSERT INTO `orders` VALUES (29, '20240909193731d9b8e2852dec401bbbb2e5958ebe6700', 0, 1391.53, 'PayPal', 0, 1, '2024-09-09 19:37:32', '2024-09-09 20:07:32');
INSERT INTO `orders` VALUES (30, '202409091937367c4922f938404a3daa6db244a68ea634', 0, 1391.53, NULL, 1, 1, '2024-09-09 19:37:37', '2024-09-09 20:07:37');
INSERT INTO `orders` VALUES (31, '2024090919390956f7fdd876724b8482b715f64f65e37c', 0, 1391.53, '支付宝', 0, 1, '2024-09-09 19:39:10', '2024-09-09 20:09:10');
INSERT INTO `orders` VALUES (32, '202409091940300e9b64a068c544a4875e539c03c6a986', 1, 1391.53, '支付宝', 0, 1, '2024-09-09 19:40:30', '2024-09-09 20:10:30');
INSERT INTO `orders` VALUES (33, '2024090919424152e6d8446ef347c38e85b4550392bd4d', 1, 1391.53, '支付宝', 0, 1, '2024-09-09 19:42:42', '2024-09-09 20:12:42');
INSERT INTO `orders` VALUES (34, '20240909194326bb19f433aab14bb0b57898027c39f2ab', 1, 1607.19, '支付宝', 0, 1, '2024-09-09 19:43:26', '2024-09-09 20:13:26');
INSERT INTO `orders` VALUES (35, '20240909215957cc505972d7794481bb2ed0fac3710b1f', 0, 1391.53, '支付宝', 1, 1, '2024-09-09 21:59:58', '2024-09-09 22:29:58');

SET FOREIGN_KEY_CHECKS = 1;
