-- SWEN 383 myPLS Database

DROP DATABASE IF EXISTS mypls;
CREATE DATABASE mypls;
USE mypls;

DROP TABLE IF EXISTS user;
CREATE TABLE user (
  userEmail VARCHAR(100) NOT NULL,
  fName VARCHAR(75) NOT NULL,
  lName VARCHAR(75) NOT NULL,
  accountType TINYINT (3),
  verified BOOLEAN NOT NULL,
  userPassword VARCHAR(20) NOT NULL ,
  PRIMARY KEY (userEmail),

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE class (
classID int AUTO_INCREMENT,
creatorEmail VARCHAR (100) NOT NULL, -- FK user
professorEmail VARCHAR(100)NOT NULL, -- FK user
className VARCHAR(100) NOT NULL,
classCode VARCHAR(20) NOT NULL,
learningObj TEXT,
learningOutcome TEXT,
beginDate DATE,
endDate DATE,
PRIMARY KEY (classID),
-- creatorEmail references the userEmail in user table
CONSTRAINT fk_creatorclass FOREIGN KEY (creatorEmail) REFERENCES user (userEmail),
-- professorEmail references the userEmail in the user table
CONSTRAINT fk_professorclass FOREIGN KEY (professorEmail) REFERENCES user (userEmail)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE classGrade (
userEmail VARCHAR(100), -- FK user
classID INT NOT NULL, -- FK class
grade DOUBLE,
PRIMARY KEY (userEmail, classID),
-- userEmail references the userEmail in the user table
CONSTRAINT fk_userEmailclassGrade FOREIGN KEY (userEmail) REFERENCES user (userEmail),
-- classID references the classID in the class table
CONSTRAINT fk_classIDclassGrade FOREIGN KEY (classID) REFERENCES class (classID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE classListLookup (
userEmail VARCHAR(100), -- FK user
classID INT, -- FK class
lectureID INT NOT NULL,
PRIMARY KEY (userEmail, classID),
-- userEmail references the userEmail in the user table
CONSTRAINT fk_userEmailclassListLookup FOREIGN KEY (userEmail) REFERENCES user (userEmail),
-- classID references the classID in the class table
CONSTRAINT fk_classIDclassListLookup FOREIGN KEY (classID) REFERENCES class (classID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE lectures (
lectureID INT,
multimediaID INT, -- datatype sub until we find one for files and stuff
PRIMARY KEY (lectureID),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE lectureLookup (
classID INT, -- FK class
lectureID INT NOT NULL, -- FK lectures
PRIMARY KEY (classID, userID),
-- classID references the classID in the class table
CONSTRAINT fk_classIDlectureLookup FOREIGN KEY (classID) REFERENCES class (classID),
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
classID INT, -- FK class
preReqID INT, -- FK class
PRIMARY KEY (classID),
-- classID references classID in the class table
CONSTRAINT classIDprerequisitesLookup FOREIGN KEY (classID) REFERENCES class (classID),
-- prereqID references prereqID in the class table
CONSTRAINT preReqIDprerequisitesLookup FOREIGN KEY (preReqID) REFERENCES class (preReqID)
FOREIGN KEY (preReqID) REFERENCES class (preReqID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE quiz (
quizID INT AUTO_INCREMENT,
timeLimit DOUBLE,
PRIMARY KEY (quizID),
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
classID INT, -- FK class
rating INT,
PRIMARY KEY (userEmail, classID),
-- userEmail references the userEmail in the user table
CONSTRAINT userEmailclassRating FOREIGN KEY (userEmail) REFERENCES user (userEmail),
-- classID references the classID in the class table
CONSTRAINT ratingClassRating FOREIGN KEY (classID) REFERENCES class (classID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE quizQuestions (
quizID INT, -- FK quiz
quizQuestionID INT AUTO_INCREMENT,
questionNum INT,
questionContent TEXT,
questionAnswer TEXT,
PRIMARY KEY (quizID, quizQuestionID),
-- quizID references the quizID in the quiz table
CONSTRAINT quizIDquizQuestions  FOREIGN KEY (quizID) REFERENCES quiz(quizID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;