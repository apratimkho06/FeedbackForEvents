package com.example.feedback;

import com.example.feedback.database.Responses;

import java.util.ArrayList;

public class Util {

    //Arts
    public Responses[] ques_list_1 = { new Responses("Venue of event", "type_rate"),
                                            new Responses("Duration of event", "type_rate"),
                                            new Responses("Smallest even number", "type_rate"),
                                            new Responses("Clarity of online information(Rules, FAQ, etc.)", "type_rate"),
                                            new Responses("Quality of the materials provided", "type_rate"),
                                            new Responses("Any suggestions or complaints to improve the event?", "type_text"),
                                            new Responses("Do you have any new event suggestions with regards to Arts?","type_text")
    };

    public Responses[] ques_list_2 = { new Responses("Venue of event", "type_rate"),
            new Responses("Smallest even number", "type_rate"),
            new Responses("Clarity of online information(Rules, FAQ, etc.)", "type_rate"),
            new Responses("Quality of the materials provided", "type_rate"),
            new Responses("Setting of stage", "type_rate"),
            new Responses("Backstage facilities", "type_rate"),
            new Responses("Any suggestions or complaints to improve the event?", "type_text"),
            new Responses("Do you have any new event suggestions with regards to Arts?","type_text")
    };

    public Responses[] ques_list_3 = { new Responses("Venue of event", "type_rate"),
            new Responses("Duration of event", "type_rate"),
            new Responses("Smallest even number", "type_rate"),
            new Responses("Clarity of online information(Rules, FAQ, etc.)", "type_rate"),
            new Responses("Quality of the materials provided", "type_rate"),
            new Responses("Cleanliness of workspace", "type_rate"),
            new Responses("Any suggestions or complaints to improve the event?", "type_text"),
            new Responses("Do you have any new event suggestions with regards to Arts?","type_text")
    };

    public Responses[] ques_list_4 = { new Responses("Venue of event", "type_rate"),
            new Responses("Duration of event", "type_rate"),
            new Responses("Prime number between 2 and 7", "type_rate"),
            new Responses("Clarity of online information(Rules, FAQ, etc.)", "type_rate"),
            new Responses("Quality of the materials provided", "type_rate"),
            new Responses("Any suggestions or complaints to improve the event?", "type_text"),
            new Responses("Do you have any new event suggestions with regards to Arts?","type_text")
    };

    public Responses[] ques_list_5 = { new Responses("Venue of the event", "type_rate"),
            new Responses("Duration of event","type_rate"),
            new Responses("Fairness of judgement","type_rate"),
            new Responses("Smallest even number","type_rate"),
            new Responses("Cleanliness of wall", "type_rate"),
            new Responses("Quality of the materials provided", "type_rate"),
            new Responses("Any suggestions or complaints to improve the event?", "type_text"),
            new Responses("Was the environment hygienic? Were there any problems you faced due to the atmosphere of the venue?", "type_text")
    };

    //Dance
    public Responses[] ques_list_6 = { new Responses("Venue of event", "type_rate"),
            new Responses("Ease of registration", "type_rate"),
            new Responses("Clarity of online information(Rules, FAQ, etc.)", "type_rate"),
            new Responses("Quality of the audio arrangements provided", "type_rate"),
            new Responses("Was the timing suitable for you?", "type_radio"),
            new Responses("Any suggestions or complaints to improve the event?", "type_text")
    };

    public Responses[] ques_list_7 = { new Responses("Venue of event", "type_rate"),
            new Responses("Ease of registration", "type_rate"),
            new Responses("Accessibility of organizers", "type_rate"),
            new Responses("How challenging were the on-spot choreography rounds?", "type_rate"),
            new Responses("Was the timing of the event suitable for you?", "type_radio"),
            new Responses("What did you like the most about the event", "type_text")
    };

    public Responses[] ques_list_8 = { new Responses("Ease of the registration", "type_rate"),
            new Responses("Directions to reach the venue", "type_rate"),
            new Responses("Venue of the event", "type_rate"),
            new Responses("Accessibility of organizers", "type_rate"),
            new Responses("Was the timing of the event suitable for you?", "type_radio"),
            new Responses("What did you like the most about the event", "type_text")
    };

    public Responses[] ques_list_9 = { new Responses("Ease of the registration", "type_rate"),
            new Responses("Venue of the event", "type_rate"),
            new Responses("Clarity of online information(Rules, FAQ, etc.)", "type_rate"),
            new Responses("Quality of the audio arrangements provided", "type_rate"),
            new Responses("Did you find the format of the competition appropriate?", "type_radio"),
            new Responses("What did you like the most about the event", "type_text")
    };

    public Responses[] ques_list_10 = { new Responses("Ease of the registration", "type_rate"),
            new Responses("Instructions to reach the venue", "type_rate"),
            new Responses("Venue of the event", "type_rate"),
            new Responses("Quality of the audio arrangements provided", "type_rate"),
            new Responses("Was the timing suitable for you?", "type_radio"),
            new Responses("Any complaints/suggestions?", "type_text")
    };

    //Dramatics
    public Responses[] ques_list_11 = { new Responses("Venue of the event", "type_rate"),
            new Responses("Quality and usability of the audio equipments provided", "type_rate"),
            new Responses("Ambience and lighting of the stage", "type_rate"),
            new Responses("Accessiblity of organizers", "type_rate"),
            new Responses("Rate the audience reception", "type_rate"),
            new Responses("Were you satisfied with the time duration of the plays?","type_radio"),
            new Responses("Any complaints/suggestions?", "type_text")
    };

    public Responses[] ques_list_12 = { new Responses("Venue of the event", "type_rate"),
            new Responses("Duration of the event", "type_rate"),
            new Responses("Clarity of online information(Rules, FAQ, etc.)", "type_rate"),
            new Responses("How challenging was the event?", "type_rate"),
            new Responses("Did the event help you boost your acting and creative skills?", "type_radio"),
            new Responses("Any complaints/suggestions?", "type_text")
    };

    //Fashion
    public Responses[] ques_list_13 = { new Responses("Dressing rooms", "type_rate"),
            new Responses("Stage setup", "type_rate"),
            new Responses("Clarity of online information(Rules, FAQ, etc.)", "type_rate"),
            new Responses("Quality of lighting and audio equipment","type_rate"),
            new Responses("Duration","type_rate"),
            new Responses("Have you ever attended any other fashion competition? If yes then how different was it from Festember's?", "type_text"),
            new Responses("Any complaints/suggestions?", "type_text")
    };

    public Responses[] ques_list_14 = { new Responses("Dressing rooms", "type_rate"),
            new Responses("Stage setup", "type_rate"),
            new Responses("Clarity of online information(Rules, FAQ, etc.)", "type_rate"),
            new Responses("Quality of lighting and audio equipment","type_rate"),
            new Responses("Duration","type_rate"),
            new Responses("Quality of Judging panel","type_rate"),
            new Responses("Have you ever attended any other fashion competition? If yes then how different was it from Festember's?", "type_text"),
            new Responses("Any complaints/suggestions?", "type_text")
    };

    //Music
    public Responses[] ques_list_15 = { new Responses("Ease of registration", "type_rate"),
            new Responses("Venue of the event", "type_rate"),
            new Responses("Clarity of online information(Rules, FAQ, etc.)", "type_rate"),
            new Responses("Quality of audio equipments at the venue", "type_rate"),
            new Responses("Was the setup and performance time sufficient?", "type_radio"),
            new Responses("Any complaints/suggestions?", "type_text")
    };

    public Responses[] ques_list_16 = { new Responses("Ease of registration", "type_rate"),
            new Responses("Venue of the event", "type_rate"),
            new Responses("Clarity of online information(Rules, FAQ, etc.)", "type_rate"),
            new Responses("Quality of audio equipments at the venue", "type_rate"),
            new Responses("Was the setup and performance time sufficient?", "type_radio"),
            new Responses("Any complaints/suggestions?", "type_text")
    };

    public Responses[] ques_list_17 = { new Responses("Ease of registration", "type_rate"),
            new Responses("Accessiblity of organizers and venue","type_rate"),
            new Responses("Stage setup","type_rate"),
            new Responses("Clarity of online information(Rules, FAQ, etc.)", "type_rate"),
            new Responses("Quality of audio equipments at the venue(speakers, mics, etc.)", "type_rate"),
            new Responses("Was the setup time and performance time sufficient?", "type_radio"),
            new Responses("What did you like the most about the event?", "type_text")
    };

    public Responses[] ques_list_18 = { new Responses("Ease of registration", "type_rate"),
            new Responses("Accessiblity of organizers and venue","type_rate"),
            new Responses("Stage setup","type_rate"),
            new Responses("Clarity of online information(Rules, FAQ, etc.)", "type_rate"),
            new Responses("Quality of audio equipments at the venue(speakers, mics, etc.)", "type_rate"),
            new Responses("Was the setup time and performance time sufficient?", "type_radio"),
            new Responses("What did you like the most about the event?", "type_text")
    };

    //Shrutilaya
    public Responses[] ques_list_19 = { new Responses("Venue and stage setup", "type_rate"),
            new Responses("Time allotted per candidate","type_rate"),
            new Responses("Clarity of online information(Rules, FAQ, etc.)", "type_rate"),
            new Responses("Audio equipments(mics,speakers) provided", "type_rate"),
            new Responses("Accessiblity of organizers","type_rate"),
            new Responses("Any complaints/suggestions?", "type_text"),
            new Responses("How did you get to know about this event?","type_text")
    };

    public Responses[] ques_list_20 = { new Responses("Venue and stage setup", "type_rate"),
            new Responses("Time allotted per candidate","type_rate"),
            new Responses("Clarity of online information(Rules, FAQ, etc.)", "type_rate"),
            new Responses("Audio equipments(mics,speakers) provided", "type_rate"),
            new Responses("Accessiblity of organizers","type_rate"),
            new Responses("Any complaints/suggestions?", "type_text"),
            new Responses("How did you get to know about this event?","type_text")
    };

    public Responses[] ques_list_21 = { new Responses("Venue and stage setup", "type_rate"),
            new Responses("Time allotted per candidate","type_rate"),
            new Responses("Clarity of online information(Rules, FAQ, etc.)", "type_rate"),
            new Responses("Audio equipments(mics,speakers) provided", "type_rate"),
            new Responses("Accessiblity of organizers","type_rate"),
            new Responses("Any complaints/suggestions?", "type_text"),
            new Responses("How did you get to know about this event?","type_text")
    };

    public ArrayList<Responses[]> event_list = new ArrayList<>();
}
