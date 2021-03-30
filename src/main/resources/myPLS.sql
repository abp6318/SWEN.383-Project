-- SWEN 383 myPLS Database

DROP DATABASE IF EXISTS mypls;
CREATE DATABASE mypls;
USE mypls;

DROP TABLE IF EXISTS user;
CREATE TABLE user (
  userEmail VARCHAR(100) NOT NULL,
  fName VARCHAR(75) NOT NULL,
  lName VARCHAR(75) NOT NULL,
  accountType CHAR (1),
  verified BOOLEAN NOT NULL,
  userPassword VARCHAR(40) NOT NULL ,
  PRIMARY KEY (userEmail)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE class (
classCode VARCHAR(20) NOT NULL,
creatorEmail VARCHAR (100) NOT NULL, -- FK user
professorEmail VARCHAR(100)NOT NULL, -- FK user
className VARCHAR(100) NOT NULL,
learningObj TEXT,
learningOutcome TEXT,
beginDate DATE,
endDate DATE,
PRIMARY KEY (classCode),
-- creatorEmail references the userEmail in user table
CONSTRAINT fk_creatorclass FOREIGN KEY (creatorEmail) REFERENCES user (userEmail),
-- professorEmail references the userEmail in the user table
CONSTRAINT fk_professorclass FOREIGN KEY (professorEmail) REFERENCES user (userEmail)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE classGrade (
userEmail VARCHAR(100), -- FK user
classCode VARCHAR(20), -- FK class
grade DOUBLE,
PRIMARY KEY (userEmail, classCode),
-- userEmail references the userEmail in the user table
CONSTRAINT fk_userEmailclassGrade FOREIGN KEY (userEmail) REFERENCES user (userEmail),
-- classCodereferences the classCodein the class table
CONSTRAINT fk_classCodeclassGrade FOREIGN KEY (classCode) REFERENCES class (classCode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE classListLookup (
userEmail VARCHAR(100), -- FK user
classCode VARCHAR(20), -- FK class
lectureID INT NOT NULL,
PRIMARY KEY (userEmail, classCode),
-- userEmail references the userEmail in the user table
CONSTRAINT fk_userEmailclassListLookup FOREIGN KEY (userEmail) REFERENCES user (userEmail),
-- classCodereferences the classCodein the class table
CONSTRAINT fk_classCodeclassListLookup FOREIGN KEY (classCode) REFERENCES class (classCode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE lectures (
lectureID INT,
multimediaID INT NOT NULL, 
PRIMARY KEY (lectureID, multimediaID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE lectureLookup (
classCode VARCHAR(20), -- FK class
lectureID INT, -- FK lectures
PRIMARY KEY (classCode, lectureID),
-- classCodereferences the classCodein the class table
CONSTRAINT fk_classCodelectureLookup FOREIGN KEY (classCode) REFERENCES class (classCode),
-- lectureID references the lectureID in the lectures table
CONSTRAINT fk_lectureIDlectureLookup FOREIGN KEY (lectureID) REFERENCES lectures (lectureID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE lectureRating (
userEmail VARCHAR (100), -- FK users
lectureID INT,  -- FK lectures
rating INT,
PRIMARY KEY (userEmail, lectureID),
-- userEmail references the userEmail in the user table
CONSTRAINT userEmaillectureRating FOREIGN KEY (userEmail) REFERENCES user (userEmail),
-- lectureID references the lectureID in the lectures table
CONSTRAINT lectureIDlectureRating FOREIGN KEY (lectureID) REFERENCES lectures (lectureID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE prerequisitesLookup (
classCode VARCHAR(20), -- FK class
preReqClassCode VARCHAR(20), -- FK class
PRIMARY KEY (classCode, preReqClassCode),
-- classCodereferences classCodein the class table
CONSTRAINT classCodeprerequisitesLookup FOREIGN KEY (classCode) REFERENCES class (classCode),
-- prereqID references prereqID in the class table
CONSTRAINT preReqIDprerequisitesLookup FOREIGN KEY (preReqClassCode) REFERENCES class (classCode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE quiz (
quizID INT AUTO_INCREMENT,
timeLimit DOUBLE,
PRIMARY KEY (quizID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE quizUserScore (
quizID INT, -- FK quiz
userEmail VARCHAR(100), -- FK user
score DOUBLE,
PRIMARY KEY (quizID, userEmail),
-- quizID references quizID in the quiz table
CONSTRAINT quizIDquizUserScore FOREIGN KEY (quizID) REFERENCES quiz (quizID),
-- userEmail references userEmail in the user email
CONSTRAINT userEmailquizUSerScore FOREIGN KEY (userEmail) REFERENCES user (userEmail)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE classRating (
userEmail VARCHAR(100), -- FK user
classCode VARCHAR(20), -- FK class
rating INT,
PRIMARY KEY (userEmail, classCode),
-- userEmail references the userEmail in the user table
CONSTRAINT userEmailclassRating FOREIGN KEY (userEmail) REFERENCES user (userEmail),
-- classCodereferences the classCodein the class table
CONSTRAINT ratingClassRating FOREIGN KEY (classCode) REFERENCES class (classCode)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE quizQuestions (
quizID INT, -- FK quiz
questionNum INT,
questionContent TEXT,
questionAnswer TEXT,
PRIMARY KEY (quizID),
-- quizID references the quizID in the quiz table
CONSTRAINT quizIDquizQuestions  FOREIGN KEY (quizID) REFERENCES quiz(quizID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE discussionGroups (
  discussionID INT AUTO_INCREMENT,
  groupName VARCHAR(25),
  userEmail VARCHAR(100) NOT NULL, -- FK user
  PRIMARY KEY (discussionID),
  CONSTRAINT userEmaildiscussionGroups FOREIGN KEY (userEmail) REFERENCES user(userEmail)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE discussionMessages (
  discussionID INT, -- FK discussionGroups
  messages TEXT,
  userEmail VARCHAR(100) NOT NULL, -- FK user
  datePosted DATE,
  PRIMARY KEY (discussionID),
  CONSTRAINT discussionIDdiscussionMessages FOREIGN KEY (discussionID) REFERENCES discussionGroups(discussionID),
  CONSTRAINT userEmaildiscussionMessages FOREIGN KEY (userEmail) REFERENCES user(userEmail)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;