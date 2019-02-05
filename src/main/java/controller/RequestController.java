package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class RequestController {

    @Autowired
    private RequestService requestService;

    private static final String USER_LOGIN_REQUEST="/user/{name}";
    private static final String POST_MESSAGE_REQUEST=USER_LOGIN_REQUEST + "/post";
    private static final String VIEW_MESSAGES_REQUEST=USER_LOGIN_REQUEST + "/view";
    private static final String FOLLOW_REQUEST=USER_LOGIN_REQUEST + "/follow/{recipient}";
    private static final String TIMELINE_REQUEST=USER_LOGIN_REQUEST + "/timeline";
    private static final Logger logger = LoggerFactory.getLogger(RequestController.class);

    @RequestMapping(value = POST_MESSAGE_REQUEST, method = RequestMethod.POST)
    public ResponseEntity<Message> postMessage(
            @RequestBody Message message, @PathVariable("name") String name
    ){
        logger.info("User: " + name + " requests to post: " + message.getMessageStr());
        requestService.addMessageForUser(name,message);
        return new ResponseEntity<Message>( message, HttpStatus.OK);
    }

    @RequestMapping(value = VIEW_MESSAGES_REQUEST, method = RequestMethod.GET)
    public ResponseEntity<String> viewMessages(@PathVariable("name") String name){
        logger.info("User: " + name + " requests to view Wall");

        requestService.viewCurrentUserWall(name);

        return new ResponseEntity<String>(requestService.getOutputViewStr(), HttpStatus.OK);
    }

    @RequestMapping(value = FOLLOW_REQUEST, method = RequestMethod.PUT)
    public ResponseEntity<String> followRecipient(@PathVariable("name") String name
            ,@PathVariable("recipient") String recipient){

        logger.info("User: " + name + " requests to follow Recipient: " + recipient);
        requestService.setUserToFollowRecipient(name,recipient);

        return new ResponseEntity<String>(requestService.getOutputViewStr(), HttpStatus.OK);
    }

    @RequestMapping(value = TIMELINE_REQUEST, method = RequestMethod.GET)
    public ResponseEntity<String> viewAllMessagesFromFollowedRecipients(@PathVariable("name") String name){

        logger.info("User: " + name + " requests to view timeline of all user messages");
        requestService.viewAllMessagesFromFollowedUsers(name);

        return new ResponseEntity<String>(requestService.getOutputViewStr(), HttpStatus.OK);
    }





//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public ResponseEntity<Car> update(@RequestBody Car car) {
//
//        if (car != null) {
//            car.setMiles(car.getMiles() + 100);
//        }
//
//        // TODO: call persistence layer to update
//        return new ResponseEntity<Car>(car, HttpStatus.OK);
//    }


//    @RequestMapping(value = "/user/", method = RequestMethod.POST)
//    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
//        logger.info("Creating User : {}", user);
//
//        if (userService.isUserExist(user)) {
//            logger.error("Unable to create. A User with name {} already exist", user.getName());
//            return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " +
//                    user.getName() + " already exist."),HttpStatus.CONFLICT);
//        }
//        userService.saveUser(user);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
//        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
//    }



//    @PutMapping(value = "/hello/{name}", consumes = {"application/json"})
//    public ResponseEntity<String> putBirthdayEntry(
//            @PathVariable("name") String name, @RequestBody DateOfBirth dateOfBirth){
//
//        BirthdayEntry birthdayEntry = new BirthdayEntry(name,dateOfBirth.getDateOfBirth());
//
//        birthdayEntryService.create(birthdayEntry);
//
//        return new ResponseEntity<String>( "", HttpStatus.NO_CONTENT);
//    }
//
//    @RequestMapping(value = "/hello/{name}", method = GET)
//    public ResponseEntity<String> getBirthdayEntry(
//            @PathVariable("name") String name){
//
//        BirthdayEntry birthdayEntry =  birthdayEntryService.findByName(name);
//
//        MessageBuilder messageBuilder = new MessageBuilder(birthdayEntry);
//
//        return new ResponseEntity<String>( messageBuilder.getMessage(), HttpStatus.OK);
//
//    }
}
