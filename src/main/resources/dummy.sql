INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('abp6318@rit.edu', 'Aaron', 'Putterman', 'a', '0001', 1, '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('da7238@rit.edu', 'Darlene', 'Ardila', 'a', '0002', '1', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('dxm6029@rit.edu', 'Dominique', 'Molee', 'a', '0003', 1, '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('vms4138@rit.edu', 'Vicky', 'Soler', 'a', '0004', 1, '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('admin@rit.edu', 'This is', 'the Admin', 'a', '0005', 1, '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('professor@rit.edu', 'Abdul', 'the Professor', 'p', '0006', 1, '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('antiprofessor@rit.edu', 'Evil Professor', 'Larry', 'p', '0007', 1, '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('anotherprofessor@rit.edu', 'Albert', 'Bananaman', 'p', '0008', 1, '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('student@rit.edu', 'Lazy Student', 'Leonard', 's', '0009', 1, '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('nevil@rit.edu', 'Nevil', 'Longbottom', 's', '0010', 1, '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('harry@rit.edu', 'Harry', 'Potter', 's', '0011', 1, '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('hermoine@rit.edu', 'Hermoine', 'Grainger', 's', '0012', 1, '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('ron@rit.edu', 'Ron', 'Weasly', 's', '0013', 1, '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');

INSERT INTO `class` (`classCode`, `creatorEmail`, `professorEmail`, `className`, `learningObj`, `learningOutcome`, `beginDate`, `endDate`) VALUES ('ISTE.140', 'admin@rit.edu', 'professor@rit.edu', 'Web 1', 'Learn the basics of web development', 'Students learned how to do HTML/CSS and some JavaScript', '2021-01-25', '2021-05-08');
INSERT INTO `class` (`classCode`, `creatorEmail`, `professorEmail`, `className`, `learningObj`, `learningOutcome`, `beginDate`, `endDate`) VALUES ('ISTE.240', 'admin@rit.edu', 'professor@rit.edu', 'Web 2', 'Delve into the intermediate concepts of web development', 'Students learned more JavaScript, PHP, and phpMyAdmin', '2021-01-25', '2021-05-08');
INSERT INTO `class` (`classCode`, `creatorEmail`, `professorEmail`, `className`, `learningObj`, `learningOutcome`, `beginDate`, `endDate`) VALUES ('ISTE.340', 'admin@rit.edu', 'antiprofessor@rit.edu', 'Client Programming', 'In depth JavaScript', 'Students are capable JavaScript developers', '2021-01-25', '2021-05-08');
INSERT INTO `class` (`classCode`, `creatorEmail`, `professorEmail`, `className`, `learningObj`, `learningOutcome`, `beginDate`, `endDate`) VALUES ('ISTE.260', 'admin@rit.edu', 'antiprofessor@rit.edu', 'Designing for the User Experience', 'Learn about core design principles', 'Students understand basic design concepts', '2021-01-25', '2021-05-08');
INSERT INTO `class` (`classCode`, `creatorEmail`, `professorEmail`, `className`, `learningObj`, `learningOutcome`, `beginDate`, `endDate`) VALUES ('ISTE.252', 'admin@rit.edu', 'anotherprofessor@rit.edu', 'Foundations of Mobile Design', 'Learn how to design and develop for mobile', 'Students are now trained in prototyping and developing mobile applications (web, native, and hybrid)', '2021-01-25', '2021-05-08');
INSERT INTO `class` (`classCode`, `creatorEmail`, `professorEmail`, `className`, `learningObj`, `learningOutcome`, `beginDate`, `endDate`) VALUES ('SWEN.383', 'admin@rit.edu', 'anotherprofessor@rit.edu', 'Software Design Principles and Patterns', 'Learn how to work in teams and about SDLCs', 'Students implemented what they learned in a semester long project', '2021-01-25', '2021-05-08');

-- prereq
INSERT INTO `prerequisitesLookup` (`classCode`, `preReqClassCode`) VALUES ('ISTE.240', 'ISTE.140');
INSERT INTO `prerequisitesLookup` (`classCode`, `preReqClassCode`) VALUES ('ISTE.340', 'ISTE.240');
INSERT INTO `prerequisitesLookup` (`classCode`, `preReqClassCode`) VALUES ('ISTE.252', 'ISTE.260');
INSERT INTO `prerequisitesLookup` (`classCode`, `preReqClassCode`) VALUES ('SWEN.383', 'ISTE.240');

-- classRating
INSERT INTO `classRating` (`userEmail`, `classCode`, `rating`) VALUES ('student@rit.edu', 'ISTE.340', 6);
INSERT INTO `classRating` (`userEmail`, `classCode`, `rating`) VALUES ('student@rit.edu', 'SWEN.383', 8);
INSERT INTO `classRating` (`userEmail`, `classCode`, `rating`) VALUES ('student@rit.edu', 'ISTE.252', 8);
INSERT INTO `classRating` (`userEmail`, `classCode`, `rating`) VALUES ('nevil@rit.edu', 'ISTE.340', 7);
INSERT INTO `classRating` (`userEmail`, `classCode`, `rating`) VALUES ('nevil@rit.edu', 'SWEN.383', 9);
INSERT INTO `classRating` (`userEmail`, `classCode`, `rating`) VALUES ('nevil@rit.edu', 'ISTE.252', 9);
INSERT INTO `classRating` (`userEmail`, `classCode`, `rating`) VALUES ('harry@rit.edu', 'ISTE.340', 4);
INSERT INTO `classRating` (`userEmail`, `classCode`, `rating`) VALUES ('harry@rit.edu', 'SWEN.383', 3);
INSERT INTO `classRating` (`userEmail`, `classCode`, `rating`) VALUES ('harry@rit.edu', 'ISTE.252', 1);

-- discussion groups
INSERT INTO `discussionGroups` (`discussionID`, `groupName`, `userEmail`) VALUES (20, 'SWEN DREAM TEAM', 'admin@rit.edu');
INSERT INTO `discussionGroups` (`discussionID`, `groupName`, `userEmail`) VALUES (21, 'The evil team', 'admin@rit.edu');
INSERT INTO `discussionGroups` (`discussionID`, `groupName`, `userEmail`) VALUES (22, 'UX Club', 'admin@rit.edu');
INSERT INTO `discussionGroups` (`discussionID`, `groupName`, `userEmail`) VALUES (23, 'Localhost', 'admin@rit.edu');
INSERT INTO `discussionGroups` (`discussionID`, `groupName`, `userEmail`) VALUES (24, 'Women in Computing', 'admin@rit.edu');
INSERT INTO `discussionGroups` (`discussionID`, `groupName`, `userEmail`) VALUES (25, 'Gamers', 'abp6318@rit.edu');
INSERT INTO `discussionGroups` (`discussionID`, `groupName`, `userEmail`) VALUES (26, 'Kicking Butt @ Interviews', 'vms4138@rit.edu');
INSERT INTO `discussionGroups` (`discussionID`, `groupName`, `userEmail`) VALUES (27, 'Outlander Fan Club', 'da7238@rit.edu');
INSERT INTO `discussionGroups` (`discussionID`, `groupName`, `userEmail`) VALUES (28, 'Spark Geniuses', 'dxm6029@rit.edu');

-- discussion group members
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (20, 'admin@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (20, 'abp6318@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (20, 'vms4138@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (20, 'da7238@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (20, 'dxm6029@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (20, 'professor@rit.edu');

INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (21, 'admin@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (21, 'abp6318@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (21, 'vms4138@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (21, 'da7238@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (21, 'dxm6029@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (21, 'professor@rit.edu');

INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (22, 'admin@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (22, 'professor@rit.edu');

INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (23, 'admin@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (23, 'professor@rit.edu');

INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (24, 'admin@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (24, 'abp6318@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (24, 'vms4138@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (24, 'da7238@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (24, 'dxm6029@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (24, 'professor@rit.edu');

INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (25, 'abp6318@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (25, 'professor@rit.edu');

INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (26, 'vms4138@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (26, 'professor@rit.edu');

INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (27, 'da7238@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (27, 'professor@rit.edu');

INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (28, 'dxm6029@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (28, 'professor@rit.edu');

-- lessons
INSERT INTO lesson (classCode, lessonName, startTime, endTime) VALUES ('ISTE.140', 'Patterns Lesson', '2021-03-21 08:00:00', '2021-04-15 09:15:00');
INSERT INTO lesson (classCode, lessonName, startTime, endTime) VALUES ('ISTE.140', 'Eating Lesson', '2021-03-21 08:01:00', '2021-04-15 09:16:00');
INSERT INTO lesson (classCode, lessonName, startTime, endTime) VALUES ('ISTE.140', 'Running Lesson', '2021-03-21 08:02:00', '2021-04-15 09:17:00');
INSERT INTO lesson (classCode, lessonName, startTime, endTime) VALUES ('ISTE.140', 'Skiing Lesson', '2021-03-21 08:03:00', '2021-04-15 09:18:00');
INSERT INTO lesson (classCode, lessonName, startTime, endTime) VALUES ('ISTE.140', 'Milkshake Lesson', '2021-03-21 08:04:00', '2021-04-15 09:19:00');

-- lectures
INSERT INTO lectures (lectureName, multimedia, lessonID) VALUES ('Multimedia example 1', 'https://docs.google.com/document/d/1zZRII4WU4gzglWI7ne860p9pE5GiAGckVaDP-q2aai4/edit?usp=sharing', '4');
INSERT INTO lectures (lectureName, multimedia, lessonID) VALUES ('Multimedia example 2', 'https://docs.google.com/document/d/1zZRII4WU4gzglWI7ne860p9pE5GiAGckVaDP-q2aai4/edit?usp=sharing', '4');
INSERT INTO lectures (lectureName, multimedia, lessonID) VALUES ('Multimedia example 3', 'https://docs.google.com/document/d/1zZRII4WU4gzglWI7ne860p9pE5GiAGckVaDP-q2aai4/edit?usp=sharing', '4');
INSERT INTO lectures (lectureName, multimedia, lessonID) VALUES ('Multimedia example 4', 'https://docs.google.com/document/d/1zZRII4WU4gzglWI7ne860p9pE5GiAGckVaDP-q2aai4/edit?usp=sharing', '4');
INSERT INTO lectures (lectureName, multimedia, lessonID) VALUES ('Multimedia example 5', 'https://docs.google.com/document/d/1zZRII4WU4gzglWI7ne860p9pE5GiAGckVaDP-q2aai4/edit?usp=sharing', '4');
INSERT INTO lectures (lectureName, multimedia, lessonID) VALUES ('Multimedia example 6', 'https://docs.google.com/document/d/1zZRII4WU4gzglWI7ne860p9pE5GiAGckVaDP-q2aai4/edit?usp=sharing', '4');

INSERT INTO lectures (lectureName, multimedia, lessonID) VALUES ('Multimedia example 11', 'https://docs.google.com/document/d/1zZRII4WU4gzglWI7ne860p9pE5GiAGckVaDP-q2aai4/edit?usp=sharing', '14');
INSERT INTO lectures (lectureName, multimedia, lessonID) VALUES ('Multimedia example 21', 'https://docs.google.com/document/d/1zZRII4WU4gzglWI7ne860p9pE5GiAGckVaDP-q2aai4/edit?usp=sharing', '14');
INSERT INTO lectures (lectureName, multimedia, lessonID) VALUES ('Multimedia example 31', 'https://docs.google.com/document/d/1zZRII4WU4gzglWI7ne860p9pE5GiAGckVaDP-q2aai4/edit?usp=sharing', '14');
INSERT INTO lectures (lectureName, multimedia, lessonID) VALUES ('Multimedia example 41', 'https://docs.google.com/document/d/1zZRII4WU4gzglWI7ne860p9pE5GiAGckVaDP-q2aai4/edit?usp=sharing', '14');
INSERT INTO lectures (lectureName, multimedia, lessonID) VALUES ('Multimedia example 51', 'https://docs.google.com/document/d/1zZRII4WU4gzglWI7ne860p9pE5GiAGckVaDP-q2aai4/edit?usp=sharing', '14');
INSERT INTO lectures (lectureName, multimedia, lessonID) VALUES ('Multimedia example 61', 'https://docs.google.com/document/d/1zZRII4WU4gzglWI7ne860p9pE5GiAGckVaDP-q2aai4/edit?usp=sharing', '14');

INSERT INTO lectures (lectureName, multimedia, lessonID) VALUES ('Multimedia example 12', 'https://docs.google.com/document/d/1zZRII4WU4gzglWI7ne860p9pE5GiAGckVaDP-q2aai4/edit?usp=sharing', '24');
INSERT INTO lectures (lectureName, multimedia, lessonID) VALUES ('Multimedia example 22', 'https://docs.google.com/document/d/1zZRII4WU4gzglWI7ne860p9pE5GiAGckVaDP-q2aai4/edit?usp=sharing', '24');
INSERT INTO lectures (lectureName, multimedia, lessonID) VALUES ('Multimedia example 32', 'https://docs.google.com/document/d/1zZRII4WU4gzglWI7ne860p9pE5GiAGckVaDP-q2aai4/edit?usp=sharing', '24');
INSERT INTO lectures (lectureName, multimedia, lessonID) VALUES ('Multimedia example 42', 'https://docs.google.com/document/d/1zZRII4WU4gzglWI7ne860p9pE5GiAGckVaDP-q2aai4/edit?usp=sharing', '24');
INSERT INTO lectures (lectureName, multimedia, lessonID) VALUES ('Multimedia example 52', 'https://docs.google.com/document/d/1zZRII4WU4gzglWI7ne860p9pE5GiAGckVaDP-q2aai4/edit?usp=sharing', '24');
INSERT INTO lectures (lectureName, multimedia, lessonID) VALUES ('Multimedia example 62', 'https://docs.google.com/document/d/1zZRII4WU4gzglWI7ne860p9pE5GiAGckVaDP-q2aai4/edit?usp=sharing', '24');

-- lesson rating (these are all admin accounts, but whatever)
INSERT INTO lessonrating (userEmail, lessonID, rating) VALUES ('abp6318@rit.edu', '4', '5');
INSERT INTO lessonrating (userEmail, lessonID, rating) VALUES ('da7238@rit.edu', '4', '6');
INSERT INTO lessonrating (userEmail, lessonID, rating) VALUES ('dxm6029@rit.edu', '4', '7');
INSERT INTO lessonrating (userEmail, lessonID, rating) VALUES ('vms4138@rit.edu', '4', '8');

INSERT INTO lessonrating (userEmail, lessonID, rating) VALUES ('abp6318@rit.edu', '14', '4');
INSERT INTO lessonrating (userEmail, lessonID, rating) VALUES ('da7238@rit.edu', '14', '5');
INSERT INTO lessonrating (userEmail, lessonID, rating) VALUES ('dxm6029@rit.edu', '14', '6');
INSERT INTO lessonrating (userEmail, lessonID, rating) VALUES ('vms4138@rit.edu', '14', '7');

INSERT INTO lessonrating (userEmail, lessonID, rating) VALUES ('abp6318@rit.edu', '24', '3');
INSERT INTO lessonrating (userEmail, lessonID, rating) VALUES ('da7238@rit.edu', '24', '4');
INSERT INTO lessonrating (userEmail, lessonID, rating) VALUES ('dxm6029@rit.edu', '24', '5');
INSERT INTO lessonrating (userEmail, lessonID, rating) VALUES ('vms4138@rit.edu', '24', '6');

-- discussion messages
INSERT INTO discussionmessages (discussionID, messages, userEmail, datePosted) VALUES ('20', 'Wow cool discussion', 'abp6318@rit.edu', '2021-04-15');
INSERT INTO discussionmessages (discussionID, messages, userEmail, datePosted) VALUES ('20', 'Yeah nice job xD', 'da7238@rit.edu', '2021-04-15');
INSERT INTO discussionmessages (discussionID, messages, userEmail, datePosted) VALUES ('20', 'LOL WHAT', 'dxm6029@rit.edu', '2021-04-15');
INSERT INTO discussionmessages (discussionID, messages, userEmail, datePosted) VALUES ('20', 'SHEEEEEESH', 'vms4138@rit.edu', '2021-04-15');

-- classListLookup
INSERT INTO classListLookup (userEmail, classCode) VALUES ('harry@rit.edu', 'ISTE.140');
INSERT INTO classListLookup (userEmail, classCode) VALUES ('hermoine@rit.edu', 'ISTE.140');
INSERT INTO classListLookup (userEmail, classCode) VALUES ('ron@rit.edu', 'ISTE.140');

-- quiz
INSERT INTO quiz (name, timeLimit, classCode, creatorEmail) VALUES ('Quiz #1', '60', 'ISTE.140', 'professor@rit.edu');
INSERT INTO quiz (name, timeLimit, classCode, creatorEmail) VALUES ('Quiz #2', '120', 'ISTE.140', 'professor@rit.edu');
INSERT INTO quiz (name, timeLimit, classCode, creatorEmail) VALUES ('Quiz #3', '180', 'ISTE.140', 'professor@rit.edu');

-- quizquestions
INSERT INTO quizquestions (quizID, questionNum, questionContent, questionAnswer) VALUES ('4', '1', 'What is my favorite color?', 'green');
INSERT INTO quizquestions (quizID, questionNum, questionContent, questionAnswer) VALUES ('4', '2', 'What is my favorite candy?', 'kitkat bars');
INSERT INTO quizquestions (quizID, questionNum, questionContent, questionAnswer) VALUES ('4', '3', "What is my cat's name?", 'copland');

INSERT INTO quizquestions (quizID, questionNum, questionContent, questionAnswer) VALUES ('14', '1', "What is my favorite string instrument?", 'electric bass');
INSERT INTO quizquestions (quizID, questionNum, questionContent, questionAnswer) VALUES ('14', '2', "When is my birthday?", '04-01-2001');
INSERT INTO quizquestions (quizID, questionNum, questionContent, questionAnswer) VALUES ('14', '3', "What is my middle name?", 'bergon');

INSERT INTO quizquestions (quizID, questionNum, questionContent, questionAnswer) VALUES ('24', '1', "What is my favorite type of weather?", 'rainy');
INSERT INTO quizquestions (quizID, questionNum, questionContent, questionAnswer) VALUES ('24', '2', "What is my favorite season?", 'spring');
INSERT INTO quizquestions (quizID, questionNum, questionContent, questionAnswer) VALUES ('24', '3', "Why?", 'because');

-- the answers students gave table that Vicky or I will make at some point
INSERT INTO studentAnswers (quizID, questionNum, studentEmail, studentAnswer) VALUES ('4','1', 'harry@rit.edu', 'green');
INSERT INTO studentAnswers (quizID, questionNum, studentEmail, studentAnswer) VALUES ('4','2', 'harry@rit.edu', 'm&ms');
INSERT INTO studentAnswers (quizID, questionNum, studentEmail, studentAnswer) VALUES ('4','3', 'harry@rit.edu', 'jerry');

INSERT INTO studentAnswers (quizID, questionNum, studentEmail, studentAnswer) VALUES ('4','1', 'hermoine@rit.edu', 'yellow');
INSERT INTO studentAnswers (quizID, questionNum, studentEmail, studentAnswer) VALUES ('4','2', 'hermoine@rit.edu', 'kitkat bars');
INSERT INTO studentAnswers (quizID, questionNum, studentEmail, studentAnswer) VALUES ('4','3', 'hermoine@rit.edu', 'copland');

INSERT INTO studentAnswers (quizID, questionNum, studentEmail, studentAnswer) VALUES ('4','1', 'ron@rit.edu', 'green');
INSERT INTO studentAnswers (quizID, questionNum, studentEmail, studentAnswer) VALUES ('4','2', 'ron@rit.edu', 'kitkat bars');
INSERT INTO studentAnswers (quizID, questionNum, studentEmail, studentAnswer) VALUES ('4','3', 'ron@rit.edu', 'copland');

-- quizuserscore
INSERT INTO quizUserScore (quizID, userEmail, score) VALUES ('4', 'harry@rit.edu', '33.33');
INSERT INTO quizUserScore (quizID, userEmail, score) VALUES ('4', 'hermoine@rit.edu', '66.67');
INSERT INTO quizUserScore (quizID, userEmail, score) VALUES ('4', 'ron@rit.edu', '100.00');











