import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

import java.net.HttpURLConnection;
import java.util.logging.Logger;

public class PostAdminRoute implements Route {

    // TODO: create post version of this class so that admin can interact with and
    // redirects back to this class (will then rebuild admin class table with this
    // new info)

    private static final Logger LOGGER = Logger.getLogger(PostAdminRoute.class.getName());

    private UserManager manager;

    public PostAdminRoute(UserManager manager, TemplateEngine engine) {
        this.manager = manager;
        LOGGER.config("PostAdminRoute Created");
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("POST /admin");

        // adjust these for values allowed by the given admin actions
        String email = request.queryParams("email");
        String password = request.queryParams("password");

        // TODO: add methods to create class, update class, and delete class in the
        // handle method here and handle logic accordingly
        // fill in ratings dynamically? - needs to be done in separate DB call I think?
        // TODO: add method to add a professor to a course in the handle method here and
        // handle logic accordingly

        // start updateClass functionality
        String updateClassID = request.queryParams("UpdateClassID");
        String updateClassName = request.queryParams("UpdateClassName");
        String updateClassProfessor = request.queryParams("UpdateClassProfessor");
        String updateClassLearningObjective = request.queryParams("UpdateClassLearningObjective");
        String updateClassLearningOutcome = request.queryParams("UpdateClassLearningOutcome");
        String updateClassStartDate = request.queryParams("UpdateClassStartDate");
        String updateClassEndDate = request.queryParams("UpdateClassEndDate");
        String addPreReq = request.queryParams("AddPreReq");
        String deletePreReq = request.queryParams("DeletePreReq");
        String addMemberEmail = request.queryParams("AddMemberEmail");
        String deleteMemberEmail = request.queryParams("DeleteMemberEmail");
        String addDiscussionID = request.queryParams("AddDiscussionID");
        String deleteDiscussionID = request.queryParams("DeleteDiscussionID");



        // update Class Name
        if (updateClassName != null && !updateClassName.equals("")) {
            manager.updateClassNameSQL(updateClassName, updateClassID);
        }

        // update professor email
        LOGGER.info("checking prof email");
        if (updateClassProfessor != null && !updateClassProfessor.equals("")) {
            LOGGER.info("prof email exists");
            if (updateClassProfessor.contains("@")) {
                LOGGER.info("Email validated");
                manager.updateClassProfessorEmailSQL(updateClassProfessor, updateClassID);
                LOGGER.info("updated");

            }
        }

        // update learning objective
        if (updateClassLearningObjective != null && !updateClassLearningObjective.equals("")) {
            manager.updateClassLearningObjectiveSQL(updateClassLearningObjective, updateClassID);
        }

        // update learning outcome
        if (updateClassLearningOutcome != null && !updateClassLearningOutcome.equals("")) {
            manager.updateClassLearningOutcomeSQL(updateClassLearningOutcome, updateClassID);
        }

        // update start date
        if (updateClassStartDate != null && !updateClassStartDate.equals("")) {
            manager.updateClassBeginDateSQL(updateClassStartDate, updateClassID);
        }

        // update end date
        if (updateClassEndDate != null && !updateClassEndDate.equals("")) {
            manager.updateClassEndDateSQL(updateClassEndDate, updateClassID);
        }

        // add pre-req
        if (addPreReq != null && !addPreReq.equals("")) {
            manager.insertClassPrerequisiteSQL(updateClassID, addPreReq);
        }

        // delete pre-req
        if (deletePreReq != null && !deletePreReq.equals("")) {
            manager.deletePrerequisiteSQL(updateClassID, deletePreReq);
        }

        // end updateClass functionality

        // start delete class
        String deleteClassCode = request.queryParams("DeleteClassCode");

        if (deleteClassCode != null && !deleteClassCode.equals("")) {
            // call delete method
            manager.deleteClassSQL(deleteClassCode);
        }
        // end delete class

        // start add class
        String addClassID = request.queryParams("AddClassID");
        String addClassCreatorEmail = request.queryParams("AddClassCreatorEmail");
        String addClassProfessor = request.queryParams("AddClassProfessor");
        String addClassName = request.queryParams("AddClassName");
        String addClassLearningObjective = request.queryParams("AddClassLearningObjective");
        String addClassStartDate = request.queryParams("AddClassStartDate");
        String addClassEndDate = request.queryParams("AddClassEndDate");

        if (addClassID != null && !addClassID.equals("")) {
            if (addClassCreatorEmail != null && !addClassCreatorEmail.equals("")) {
                if (addClassProfessor != null && !addClassProfessor.equals("")) {
                    if (addClassName != null && !addClassName.equals("")) {
                        if (addClassLearningObjective != null && !addClassLearningObjective.equals("")) {
                            if (addClassStartDate != null && !addClassStartDate.equals("")) {
                                if (addClassEndDate != null && !addClassEndDate.equals("")) {

                                    manager.insertClassSQL(addClassID, addClassCreatorEmail, addClassProfessor,
                                            addClassName, addClassLearningObjective, addClassStartDate,
                                            addClassEndDate);
                                }
                            }
                        }
                    }
                }
            }
        }

        // add group member
        if (addMemberEmail != null && !addMemberEmail.equals("") && addDiscussionID != null && !addDiscussionID.equals("")) {
            manager.addDiscussionGroupMembersSQL(addDiscussionID, addMemberEmail);
        }

        // add group member
        if (deleteMemberEmail != null && !deleteMemberEmail.equals("") && deleteDiscussionID != null && !deleteDiscussionID.equals("")) {
            manager.deleteDiscussionGroupMembersSQL(deleteDiscussionID, deleteMemberEmail);
        }

        response.redirect(WebServer.ADMIN, HttpURLConnection.HTTP_MOVED_PERM);

        return null;
    }

}
