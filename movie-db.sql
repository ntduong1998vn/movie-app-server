/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : movie-db

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 02/07/2020 22:27:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for actors
-- ----------------------------
DROP TABLE IF EXISTS `actors`;
CREATE TABLE `actors`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `nation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of actors
-- ----------------------------
INSERT INTO `actors` VALUES (1, 'Lưu Diệp Phi', 'luu-diep-phi.jpg', 'China');
INSERT INTO `actors` VALUES (2, 'Huỳnh Hiểu Minh', 'luu-diep-phi.jpg', 'China');
INSERT INTO `actors` VALUES (3, 'Kim Siêu Quần', 'luu-diep-phi.jpg', 'China');
INSERT INTO `actors` VALUES (4, 'Đặng Siêu', '281dfc13204975c55a7d9b8af27cfe78.jpg', 'Mỹ');
INSERT INTO `actors` VALUES (5, 'Trấn Thành', 'tran-thanh.jpg', 'Việt Nam');
INSERT INTO `actors` VALUES (6, 'HariWon', 'hari-won.jpg', 'Korea');

-- ----------------------------
-- Table structure for characters
-- ----------------------------
DROP TABLE IF EXISTS `characters`;
CREATE TABLE `characters`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `movie_id` int(11) NOT NULL,
  `actor_id` int(11) NULL DEFAULT NULL,
  `character` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKjk5cn95vpv3ireriydqqtox2c`(`actor_id`) USING BTREE,
  INDEX `FK23f14xg9mbsj5lfl0ouoifrmu`(`movie_id`) USING BTREE,
  CONSTRAINT `FK23f14xg9mbsj5lfl0ouoifrmu` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKjk5cn95vpv3ireriydqqtox2c` FOREIGN KEY (`actor_id`) REFERENCES `actors` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of characters
-- ----------------------------
INSERT INTO `characters` VALUES (1, 15, 1, 'Wiliam');
INSERT INTO `characters` VALUES (2, 15, 2, 'John');
INSERT INTO `characters` VALUES (3, 15, 3, 'Jack');
INSERT INTO `characters` VALUES (4, 16, 1, 'Adam John');
INSERT INTO `characters` VALUES (5, 17, 2, 'Smith');
INSERT INTO `characters` VALUES (7, 17, 3, 'Jessica');
INSERT INTO `characters` VALUES (8, 21, 3, 'Jennie');

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `movie_id` int(11) NOT NULL,
  `user_id` int(20) NOT NULL,
  `create_at` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKr1xv5xvew7k2aed5qu5lci3kt`(`movie_id`) USING BTREE,
  INDEX `FK8omq0tc18jd43bu5tjh6jvraq`(`user_id`) USING BTREE,
  CONSTRAINT `FK8omq0tc18jd43bu5tjh6jvraq` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKr1xv5xvew7k2aed5qu5lci3kt` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES (13, 'Excepteur amet anim commodo ipsum ad. Duis aliquip deserunt officia occaecat veniam ad velit ut excepteur. Est magna nostrud adipisicing mollit aliqua et minim dolore est elit veniam commodo.', 5, 2, '2020-06-11 17:26:44');
INSERT INTO `comments` VALUES (14, 'Excepteur cillum id quis non incididunt deserunt amet sint laboris nulla consectetur ut. Eiusmod culpa aliqua laboris minim veniam cupidatat ad mollit aliqua amet commodo do cillum eu. Esse anim sit deserunt irure eu officia laborum consectetur non non laborum do ea laboris. Commodo mollit ea sint in consectetur ad laboris esse. Adipisicing eiusmod dolore laborum nisi veniam quis est aliqua. Non eiusmod fugiat labore fugiat occaecat eiusmod culpa. Eiusmod ex culpa eu ad velit.', 26, 2, '2020-06-24 17:26:48');
INSERT INTO `comments` VALUES (15, 'Fugiat do nulla sint nisi fugiat voluptate incididunt amet laboris do aliquip. Anim culpa enim et dolore aliquip eu sit veniam cillum sunt. Sint in reprehenderit occaecat et ut consectetur ullamco. Ullamco deserunt elit pariatur sint elit. Nisi sunt consectetur reprehenderit ad ex proident deserunt excepteur pariatur eiusmod excepteur. Fugiat aliquip reprehenderit et ea aliqua sunt sunt eiusmod. Elit ex magna laboris quis ipsum esse cupidatat elit.', 22, 2, '2020-06-29 17:26:51');
INSERT INTO `comments` VALUES (16, 'Est sit est consequat amet. Duis est commodo est ea proident nostrud duis dolore aute nulla anim irure aliquip aute. Adipisicing consequat exercitation incididunt cupidatat amet tempor ipsum. Consequat quis tempor dolor sint officia dolore labore nulla consequat qui. Dolore reprehenderit ullamco non pariatur magna non velit amet pariatur. Ad dolore exercitation voluptate labore est irure ea quis ex.', 14, 1, '2020-06-22 17:26:54');
INSERT INTO `comments` VALUES (19, 'Proident mollit do id voluptate culpa commodo magna quis. Veniam cupidatat mollit sint esse eiusmod veniam nisi sint consequat est ullamco fugiat veniam. Dolore deserunt excepteur officia labore officia dolor sint ad veniam proident amet laborum esse. Aliquip reprehenderit elit consectetur laboris ea ad commodo id tempor cupidatat id pariatur ut. Minim sunt irure nulla velit deserunt reprehenderit elit eiusmod duis commodo voluptate. Labore ut dolor cillum officia voluptate sit velit voluptate qui cillum velit exercitation Lorem ipsum. Dolore sunt aliquip est aliqua mollit pariatur id tempor magna enim do commodo proident.', 23, 2, '2020-06-26 17:27:05');
INSERT INTO `comments` VALUES (21, 'Duong', 5, 1, '2020-06-28 17:27:18');
INSERT INTO `comments` VALUES (22, 'Chào mọi người', 23, 5, '2020-06-25 17:27:21');
INSERT INTO `comments` VALUES (23, 'Chào các bạn', 5, 2, '2020-06-11 17:26:44');
INSERT INTO `comments` VALUES (24, 'Chào các bạn', 5, 2, '2020-06-11 17:27:44');
INSERT INTO `comments` VALUES (25, 'Chào các bạn', 5, 2, '2020-06-11 17:28:44');
INSERT INTO `comments` VALUES (26, 'Chào các bạn', 5, 2, '2020-06-11 17:36:44');
INSERT INTO `comments` VALUES (27, 'Chào các bạn', 5, 2, '2020-06-11 17:46:44');
INSERT INTO `comments` VALUES (28, 'Chào các bạn', 5, 2, '2020-06-11 17:56:44');
INSERT INTO `comments` VALUES (29, 'Chào các bạn', 5, 2, '2020-06-11 17:32:44');
INSERT INTO `comments` VALUES (30, 'Chào các bạn', 5, 2, '2020-06-11 17:12:44');
INSERT INTO `comments` VALUES (31, 'Thêm comment', 8, 4, '2020-06-26 18:42:33');

-- ----------------------------
-- Table structure for episodes
-- ----------------------------
DROP TABLE IF EXISTS `episodes`;
CREATE TABLE `episodes`  (
  `episode_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  PRIMARY KEY (`episode_id`, `movie_id`) USING BTREE,
  INDEX `FKr0m8qnjio0s9c6s53p68nxpg`(`movie_id`) USING BTREE,
  CONSTRAINT `FKr0m8qnjio0s9c6s53p68nxpg` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of episodes
-- ----------------------------
INSERT INTO `episodes` VALUES (1, 7);
INSERT INTO `episodes` VALUES (2, 7);
INSERT INTO `episodes` VALUES (2, 8);
INSERT INTO `episodes` VALUES (1, 14);

-- ----------------------------
-- Table structure for genres
-- ----------------------------
DROP TABLE IF EXISTS `genres`;
CREATE TABLE `genres`  (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of genres
-- ----------------------------
INSERT INTO `genres` VALUES (2, 'Adventure');
INSERT INTO `genres` VALUES (5, 'Crime');
INSERT INTO `genres` VALUES (6, 'Documentary');
INSERT INTO `genres` VALUES (9, 'Fantasy');
INSERT INTO `genres` VALUES (10, 'History');
INSERT INTO `genres` VALUES (11, 'Horror');
INSERT INTO `genres` VALUES (12, 'Music');
INSERT INTO `genres` VALUES (14, 'Romance');
INSERT INTO `genres` VALUES (15, 'Science Fiction');
INSERT INTO `genres` VALUES (16, 'TV Movie');
INSERT INTO `genres` VALUES (17, 'Thriller');
INSERT INTO `genres` VALUES (18, 'War');
INSERT INTO `genres` VALUES (19, 'Western');
INSERT INTO `genres` VALUES (21, 'TV Series');

-- ----------------------------
-- Table structure for genres_movies
-- ----------------------------
DROP TABLE IF EXISTS `genres_movies`;
CREATE TABLE `genres_movies`  (
  `genre_id` tinyint(4) NOT NULL,
  `movie_id` int(11) NOT NULL,
  PRIMARY KEY (`genre_id`, `movie_id`) USING BTREE,
  INDEX `fk_genres_has_movies_movies1_idx`(`movie_id`) USING BTREE,
  INDEX `fk_genres_has_movies_genres1_idx`(`genre_id`) USING BTREE,
  CONSTRAINT `fk_genres_has_movies_genres1` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_genres_has_movies_movies1` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of genres_movies
-- ----------------------------
INSERT INTO `genres_movies` VALUES (2, 8);
INSERT INTO `genres_movies` VALUES (2, 11);
INSERT INTO `genres_movies` VALUES (2, 25);
INSERT INTO `genres_movies` VALUES (2, 26);
INSERT INTO `genres_movies` VALUES (6, 21);
INSERT INTO `genres_movies` VALUES (9, 16);
INSERT INTO `genres_movies` VALUES (9, 30);
INSERT INTO `genres_movies` VALUES (9, 31);
INSERT INTO `genres_movies` VALUES (9, 32);
INSERT INTO `genres_movies` VALUES (9, 39);
INSERT INTO `genres_movies` VALUES (9, 40);
INSERT INTO `genres_movies` VALUES (10, 16);
INSERT INTO `genres_movies` VALUES (10, 33);
INSERT INTO `genres_movies` VALUES (10, 34);
INSERT INTO `genres_movies` VALUES (10, 35);
INSERT INTO `genres_movies` VALUES (10, 41);
INSERT INTO `genres_movies` VALUES (10, 42);
INSERT INTO `genres_movies` VALUES (11, 36);
INSERT INTO `genres_movies` VALUES (11, 37);
INSERT INTO `genres_movies` VALUES (11, 38);
INSERT INTO `genres_movies` VALUES (11, 43);
INSERT INTO `genres_movies` VALUES (11, 44);
INSERT INTO `genres_movies` VALUES (12, 39);
INSERT INTO `genres_movies` VALUES (12, 40);
INSERT INTO `genres_movies` VALUES (12, 41);
INSERT INTO `genres_movies` VALUES (14, 5);
INSERT INTO `genres_movies` VALUES (14, 6);
INSERT INTO `genres_movies` VALUES (14, 7);
INSERT INTO `genres_movies` VALUES (15, 8);
INSERT INTO `genres_movies` VALUES (16, 11);
INSERT INTO `genres_movies` VALUES (16, 12);
INSERT INTO `genres_movies` VALUES (16, 13);
INSERT INTO `genres_movies` VALUES (17, 14);
INSERT INTO `genres_movies` VALUES (17, 15);
INSERT INTO `genres_movies` VALUES (17, 16);
INSERT INTO `genres_movies` VALUES (18, 17);
INSERT INTO `genres_movies` VALUES (18, 18);
INSERT INTO `genres_movies` VALUES (18, 19);
INSERT INTO `genres_movies` VALUES (19, 20);
INSERT INTO `genres_movies` VALUES (19, 21);
INSERT INTO `genres_movies` VALUES (19, 22);

-- ----------------------------
-- Table structure for movies
-- ----------------------------
DROP TABLE IF EXISTS `movies`;
CREATE TABLE `movies`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `quality` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `imdb` float NULL DEFAULT 0,
  `runtime` mediumint(9) NULL DEFAULT 0,
  `release_date` date NULL DEFAULT '1990-01-01',
  `overview` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `popularity` float NULL DEFAULT 0,
  `language` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `poster` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `view` int(11) NULL DEFAULT 0,
  `nation` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `adult` tinyint(4) NULL DEFAULT 0,
  `visible` tinyint(4) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of movies
-- ----------------------------
INSERT INTO `movies` VALUES (5, 'Joker', '', 8.4, 0, '1900-01-20', 'During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.', 352.867, 'en', '/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (6, 'Terminator: Dark Fate', '', 6.4, 0, '1900-01-20', 'More than two decades have passed since Sarah Connor prevented Judgment Day, changed the future, and re-wrote the fate of the human race. Dani Ramos is living a simple life in Mexico City with her brother and father when a highly advanced and deadly new Terminator – a Rev-9 – travels back through time to hunt and kill her. Dani\'s survival depends on her joining forces with two warriors: Grace, an enhanced super-soldier from the future, and a battle-hardened Sarah Connor. As the Rev-9 ruthlessly destroys everything and everyone in its path on the hunt for Dani, the three are led to a T-800 from Sarah’s past that may be their last best hope.', 221.789, 'en', '/vqzNJRH4YyquRiWxCCOH0aXggHI.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (7, 'Frozen II', '', 5.1, 0, '1900-01-20', 'Elsa, Anna, Kristoff and Olaf are going far in the forest to know the truth about an ancient mystery of their kingdom.', 277.172, 'en', '/qdfARIhgpgZOBh3vfNhWS4hmSo3.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (8, 'Red Shoes and the Seven Dwarfs', '', 6, 0, '1900-01-20', 'Princes who have been turned into Dwarfs seek the red shoes of a lady in order to break the spell, although it will not be easy.', 124.503, 'en', '/MBiKqTsouYqAACLYNDadsjhhC0.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (11, 'One Piece: Stampede', '', 7.3, 0, '1900-01-20', 'One Piece: Stampede is a stand-alone film that celebrates the anime\'s 20th Anniversary and takes place outside the canon of the \"One Piece\" TV series. Monkey D. Luffy and his Straw Hat pirate crew are invited to a massive Pirate Festival that brings many of the most iconic characters from throughout the franchise to participate in competition with the Straw Hats to find Roger\'s treasure. It also pits the Straw Hats against a new enemy named Bullet, a former member of Roger\'s crew.', 136.105, 'ja', '/4E2lyUGLEr3yH4q6kJxPkQUhX7n.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (12, 'Maleficent: Mistress of Evil', '', 7.2, 0, '1900-01-20', 'Maleficent and her goddaughter Aurora begin to question the complex family ties that bind them as they are pulled in different directions by impending nuptials, unexpected allies, and dark new forces at play.', 84.61, 'en', '/tBuabjEqxzoUBHfbyNbd8ulgy5j.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (13, 'Ip Man 4: The Finale', '', 0, 0, '1900-01-20', 'Ip Man 4 is an upcoming Hong Kong biographical martial arts film directed by Wilson Yip and produced by Raymond Wong. It is the fourth in the Ip Man film series based on the life of the Wing Chun grandmaster of the same name and features Donnie Yen reprising the role. The film began production in April 2018 and ended in July the same year.', 109.701, 'cn', '/vn94LlNrbUWIZZyAdmvUepFBeaY.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (14, 'Gemini Man', '', 5.6, 0, '1900-01-20', 'Henry Brogen, an aging assassin tries to get out of the business but finds himself in the ultimate battle: fighting his own clone who is 25 years younger than him and at the peak of his abilities.', 155.556, 'en', '/uTALxjQU8e1lhmNjP9nnJ3t2pRU.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (15, 'Fast & Furious Presents: Hobbs & Shaw', '', 6.6, 0, '1900-01-20', 'Ever since US Diplomatic Security Service Agent Hobbs and lawless outcast Shaw first faced off, they just have swapped smacks and bad words. But when cyber-genetically enhanced anarchist Brixton\'s ruthless actions threaten the future of humanity, both join forces to defeat him. (A spin-off of “The Fate of the Furious,” focusing on Johnson\'s Luke Hobbs and Statham\'s Deckard Shaw.)', 110.374, 'en', '/kvpNZAQow5es1tSY6XW2jAZuPPG.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (16, 'Fixed', '', 7.1, 0, '1900-01-20', 'Simba idolises his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub\'s arrival. Scar, Mufasa\'s brother—and former heir to the throne—has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba\'s exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his.', 194.319, 'en', '/2bXbqYdUdNVa8VIWXVfclP2ICtT.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (17, 'Ford v Ferrari', '', 8, 0, '1900-01-20', 'American car designer Carroll Shelby and the British-born driver Ken Miles work together to battle corporate interference, the laws of physics, and their own personal demons to build a revolutionary race car for Ford Motor Company and take on the dominating race cars of Enzo Ferrari at the 24 Hours of Le Mans in France in 1966.', 110.183, 'en', '/6ApDtO7xaWAfPqfi2IARXIzj8QS.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (18, 'Cars', '', 6.7, 0, '1900-06-20', 'Lightning McQueen, a hotshot rookie race car driven to succeed, discovers that life is about the journey, not the finish line, when he finds himself unexpectedly detoured in the sleepy Route 66 town of Radiator Springs. On route across the country to the big Piston Cup Championship in California to compete against two seasoned pros, McQueen gets to know the town\'s offbeat characters.', 80.039, 'en', '/jpfkzbIXgKZqCZAkEkFH2VYF63s.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (19, 'Aladdin', '', 7.1, 0, '1900-01-20', 'A kindhearted street urchin named Aladdin embarks on a magical adventure after finding a lamp that releases a wisecracking genie while a power-hungry Grand Vizier vies for the same lamp that has the power to make their deepest wishes come true.', 92.356, 'en', '/3iYQTLGoy7QnjcUYRJy4YrAgGvp.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (20, '23-F: la película', '', 5.6, 0, '1900-11-20', 'The failed coup d\'état of February 23, 1981, which began with the capture of the Congress of Deputies and ended with the release of parliamentarians, put at serious risk the Spanish democracy.', 48.004, 'es', '/fgw1XkdmSaD226KtIKcdNmysb3X.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (21, 'Spider-Man: Far from Home', '', 7.6, 0, '1900-01-20', 'Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.', 207.098, 'en', '/lcq8dVxeeOqHvvgcte707K0KVx5.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (22, 'El Camino: A Breaking Bad Movie', '', 7, 0, '1900-01-20', 'In the wake of his dramatic escape from captivity, Jesse Pinkman must come to terms with his past in order to forge some kind of future.', 71.492, 'en', '/ePXuKdXZuJx8hHMNr2yM4jY2L7Z.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (23, 'Doctor Sleep', '', 7.1, 0, '1900-01-20', 'A traumatized, alcoholic Dan Torrance meets Abra, a kid who also has the ability to \"shine.\" He tries to protect her from the True Knot, a cult whose goal is to feed off of people like them in order to remain immortal.', 71.968, 'en', '/p69QzIBbN06aTYqRRiCOY1emNBh.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (24, 'Dora and the Lost City of Gold', '', 6.4, 0, '1900-01-20', 'Dora, a girl who has spent most of her life exploring the jungle with her parents, now must navigate her most dangerous adventure yet: high school. Always the explorer, Dora quickly finds herself leading Boots (her best friend, a monkey), Diego, and a rag tag group of teens on an adventure to save her parents and solve the impossible mystery behind a lost Inca civilization.', 65.608, 'en', '/xvYCZ740XvngXK0FNeSNVTJJJ5v.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (25, 'Avengers: Infinity War', '', 8.3, 0, '1900-01-20', 'As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.', 84.267, 'en', '/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (26, 'Anna', '', 6.4, 0, '1900-01-20', 'Beneath Anna Poliatova\'s striking beauty lies a secret that will unleash her indelible strength and skill to become one of the world\'s most feared government assassins.', 58.093, 'fr', '/d8u4jpkDKgEPDJUl4g3vOOP3mDe.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (27, 'Ellipse', '', 4, 0, '1900-01-20', 'A man and his dog are stranded on a volatile, oval-shaped planet and are forced to adapt and escape before time destroys them both.', 118.121, 'en', '/4I0CQfnMy6sRR7QhgqsXR16TmIs.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (28, 'John Wick: Chapter 3 - Parabellum', '', 7.1, 0, '1900-01-20', 'Super-assassin John Wick returns with a $14 million price tag on his head and an army of bounty-hunting killers on his trail. After killing a member of the shadowy international assassin’s guild, the High Table, John Wick is excommunicado, but the world’s most ruthless hit men and women await his every turn.', 65.512, 'en', '/ziEuG1essDuWuC5lpWUaw1uXY2O.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (29, 'Ralph Breaks the Internet', '', 7.2, 0, '1900-01-20', 'Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope\'s video game, \"Sugar Rush.\" In way over their heads, Ralph and Vanellope rely on the citizens of the internet -- the netizens -- to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.', 47.173, 'en', '/qEnH5meR381iMpmCumAIMswcQw2.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (30, '47 Meters Down: Uncaged', '', 4.9, 0, '1900-01-20', 'A group of backpackers diving in a ruined underwater city discover that they have stumbled into the territory of the ocean\'s deadliest shark species.', 57.517, 'en', '/g4z7mDmJmx23vsVg6XNWcnXb6gc.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (31, 'Klaus', '', 8.7, 0, '1900-01-20', 'When Jesper distinguishes himself as the Postal Academy\'s worst student, he is sent to Smeerensburg, a small village located on a icy island above the Arctic Circle, where grumpy inhabitants barely exchange words, let alone letters. Jesper is about to give up and abandon his duty as a postman when he meets local teacher Alva and Klaus, a mysterious carpenter who lives alone in a cabin full of handmade toys.', 85.749, 'en', '/q125RHUDgR4gjwh1QkfYuJLYkL.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (32, 'Shipwrecked', '', 6.5, 0, '1900-01-19', 'A young Norwegian boy in 1850s England goes to work as a cabin boy and discovers some of his shipmates are actually pirates.', 29.417, 'no', '/jf2PkR0Yt0ZRMKqtJzwja7JV2Hk.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (33, 'Good Boys', '', 6.5, 0, '1900-01-20', 'A group of young boys on the cusp of becoming teenagers embark on an epic quest to fix their broken drone before their parents get home.', 45.114, 'en', '/tximyCXMEnWIIyOy9STkOduUprG.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (34, 'Dark Phoenix', '', 6.1, 0, '1900-01-20', 'The X-Men face their most formidable and powerful foe when one of their own, Jean Grey, starts to spiral out of control. During a rescue mission in outer space, Jean is nearly killed when she\'s hit by a mysterious cosmic force. Once she returns home, this force not only makes her infinitely more powerful, but far more unstable. The X-Men must now band together to save her soul and battle aliens that want to use Grey\'s new abilities to rule the galaxy.', 50.49, 'en', '/cCTJPelKGLhALq3r51A9uMonxKj.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (35, 'Lady and the Tramp', '', 7.2, 0, '1900-01-20', 'The love story between a pampered Cocker Spaniel named Lady and a streetwise mongrel named Tramp. Lady finds herself out on the street after her owners have a baby and is saved from a pack by Tramp, who tries to show her to live her life footloose and collar-free.', 64.145, 'en', '/kzJ4a0ITuMJDuX2IjFKYG6QIipW.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (36, 'Dolemite Is My Name', '', 7.2, 0, '1900-01-20', 'The story of Rudy Ray Moore, who created the iconic big screen pimp character Dolemite in the 1970s.', 38.374, 'en', '/kURLLZ10A0ZhI5bVIyOuPBzbtSB.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (37, 'Toy Story 4', '', 7.6, 0, '1900-01-20', 'Woody has always been confident about his place in the world and that his priority is taking care of his kid, whether that\'s Andy or Bonnie. But when Bonnie adds a reluctant new toy called \"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.', 53.819, 'en', '/w9kR8qbmQ01HwnvK4alvnQ2ca0L.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (38, 'Parasite', '', 8.5, 0, '1900-01-20', 'All unemployed, Ki-taek\'s family takes peculiar interest in the wealthy and glamorous Parks for their livelihood until they get entangled in an unexpected incident.', 61.276, 'ko', '/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (39, 'The King', '', 7.2, 0, '1900-01-20', 'England, 15th century. Hal, a capricious prince who lives among the populace far from court, is forced by circumstances to reluctantly accept the throne and become Henry V.', 52.01, 'en', '/8u0QBGUbZcBW59VEAdmeFl9g98N.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (40, 'Terminator Genisys', '', 5.9, 0, '1900-01-20', 'The year is 2029. John Connor, leader of the resistance continues the war against the machines. At the Los Angeles offensive, John\'s fears of the unknown future begin to emerge when TECOM spies reveal a new plot by SkyNet that will attack him from both fronts; past and future, and will ultimately change warfare forever.', 59.255, 'en', '/5JU9ytZJyR3zmClGmVm9q4Geqbd.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (41, 'It Chapter Two', '', 6.9, 0, '1900-01-20', '27 years after overcoming the malevolent supernatural entity Pennywise, the former members of the Losers\' Club, who have grown up and moved away from Derry, are brought back together by a devastating phone call.', 82.837, 'en', '/zfE0R94v1E8cuKAerbskfD3VfUt.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (42, 'John Wick', '', 7.2, 0, '1900-01-20', 'Ex-hitman John Wick comes out of retirement to track down the gangsters that took everything from him.', 56.231, 'en', '/5vHssUeVe25bMrof1HyaPyWgaP.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (43, 'The Angry Birds Movie 2', '', 6.4, 0, '1900-01-20', 'Red, Chuck, Bomb and the rest of their feathered friends are surprised when a green pig suggests that they put aside their differences and unite to fight a common threat. Aggressive birds from an island covered in ice are planning to use an elaborate weapon to destroy the fowl and swine.', 50.002, 'en', '/ebe8hJRCwdflNQbUjRrfmqtUiNi.jpg', 0, '', 0, 1);
INSERT INTO `movies` VALUES (44, 'Once Upon a Time... in Hollywood', '', 7.6, 0, '1900-01-20', 'A faded television actor and his stunt double strive to achieve fame and success in the film industry during the final years of Hollywood\'s Golden Age in 1969 Los Angeles.', 48.573, 'en', '/8j58iEBw9pOXFD2L0nt0ZXeHviB.jpg', 0, '', 0, 1);

-- ----------------------------
-- Table structure for reviews
-- ----------------------------
DROP TABLE IF EXISTS `reviews`;
CREATE TABLE `reviews`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `create_at` datetime(0) NULL DEFAULT NULL,
  `movie_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `score` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK87tlqya0rq8ijfjscldpvvdyq`(`movie_id`) USING BTREE,
  INDEX `FKcgy7qjc1r99dp117y9en6lxye`(`user_id`) USING BTREE,
  CONSTRAINT `FK87tlqya0rq8ijfjscldpvvdyq` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKcgy7qjc1r99dp117y9en6lxye` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (1, 'ROLE_USER');
INSERT INTO `roles` VALUES (2, 'ROLE_USER_VIP');
INSERT INTO `roles` VALUES (3, 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for sources
-- ----------------------------
DROP TABLE IF EXISTS `sources`;
CREATE TABLE `sources`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `src` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `server` smallint(6) NOT NULL,
  `episode_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK6xchmny0jro9vyw9p1t7cq83d`(`episode_id`, `movie_id`) USING BTREE,
  CONSTRAINT `FK6xchmny0jro9vyw9p1t7cq83d` FOREIGN KEY (`episode_id`, `movie_id`) REFERENCES `episodes` (`episode_id`, `movie_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sources
-- ----------------------------
INSERT INTO `sources` VALUES (1, 'mp4', 'Hahaha', 0, 1, 7);
INSERT INTO `sources` VALUES (2, 'org', 'dsdhsadh', 1, 1, 7);
INSERT INTO `sources` VALUES (3, 'mp4', 'asdasd', 0, 2, 7);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email_verified` bit(1) NOT NULL,
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `provider` smallint(6) NOT NULL,
  `provider_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '',
  `role` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'ROLE_USER',
  `created_at` datetime(0) NULL DEFAULT NULL,
  `created_by` bigint(20) NULL DEFAULT NULL,
  `updated_at` datetime(0) NULL DEFAULT NULL,
  `updated_by` bigint(20) NULL DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK6dotkott2kjsp8vw4d0m25fb7`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, '', 'ntduong1998vn@gmail.com', b'0', 'https://lh6.googleusercontent.com/-CDVxvPvmnIs/AAAAAAAAAAI/AAAAAAAAAAA/ACHi3re-xaZRccNeekUjIQ_f5qggAiOgoQ/photo.jpg', 'Triều Dương', '$2a$10$s2S2B.mGL5Jl7fkPim8sN.vjoUlnkOotAFrMI1eXyt/DKde3p9mFi', 1, '123', 'ROLE_USER', NULL, NULL, '2020-05-22 22:04:59', NULL, 1);
INSERT INTO `users` VALUES (2, '', 'gcltt10@gmail.com', b'0', 'https://lh6.googleusercontent.com/-CDVxvPvmnIs/AAAAAAAAAAI/AAAAAAAAAAA/ACHi3re-xaZRccNeekUjIQ_f5qggAiOgoQ/photo.jpg', 'Thanh Thanh', '$2a$10$s2S2B.mGL5Jl7fkPim8sN.vjoUlnkOotAFrMI1eXyt/DKde3p9mFi', 2, '147', 'ROLE_USER', NULL, NULL, NULL, NULL, 0);
INSERT INTO `users` VALUES (3, NULL, 'thanhthanh@gmail.com', b'0', NULL, 'Thanh Thanh', 'Duong123', 0, '', 'ROLE_USER', NULL, NULL, NULL, NULL, 1);
INSERT INTO `users` VALUES (4, NULL, 'ntduong2010vn@gmail.com', b'0', NULL, 'Nguyễn Triều Dương', 'Duong123', 0, NULL, 'ROLE_USER', '2020-05-30 20:15:52', NULL, '2020-05-30 20:15:52', NULL, 0);
INSERT INTO `users` VALUES (5, NULL, 'gcltt11@gmail.com', b'0', NULL, 'Nguyễn Triều Dương', '$2a$10$kP1s1yhbE0jlryvdpiNq6e8h96YAGlK8s/R5KKlSOs.0H75qyt14i', 0, NULL, 'ROLE_USER', '2020-05-31 10:48:56', NULL, '2020-05-31 10:48:56', NULL, 0);

-- ----------------------------
-- Table structure for users_roles
-- ----------------------------
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles`  (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `FKj6m8fwv7oqv74fcehir1a9ffy`(`role_id`) USING BTREE,
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users_roles
-- ----------------------------
INSERT INTO `users_roles` VALUES (1, 1);
INSERT INTO `users_roles` VALUES (2, 1);
INSERT INTO `users_roles` VALUES (4, 1);
INSERT INTO `users_roles` VALUES (5, 1);
INSERT INTO `users_roles` VALUES (1, 2);
INSERT INTO `users_roles` VALUES (2, 2);
INSERT INTO `users_roles` VALUES (1, 3);

SET FOREIGN_KEY_CHECKS = 1;
