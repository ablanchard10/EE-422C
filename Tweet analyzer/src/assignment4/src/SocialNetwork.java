package assignment4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Social Network consists of methods that filter users matching a
 * condition.
 *
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Get K most followed Users.
     *
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @param k
     *            integer of most popular followers to return
     * @return the set of usernames who are most mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getName()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like ethomaz@utexas.edu does NOT
     *         contain a mention of the username.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static List<String> findKMostFollower(List<Tweets> tweets, int k) {
        List<String> mostFollowers = new ArrayList<>();
        List<String> followedUsers = new ArrayList<>();
        List<Integer> numFollowers = new ArrayList<>();

        //make arrays of followers and how many times they are followed
        for(int i = 0; i < tweets.size(); i++){
            String text = tweets.get(i).getText();
            String[] arr = text.split("[ &^]");
            for(int j = 0; j < arr.length; j++){
                if(arr[j].length() > 0 && arr[j].charAt(0) == '@'){
                    String user = arr[j].substring(1);
                    if(!user.contains("@")) {
                        if (!followedUsers.contains(user.toLowerCase())) {
                            followedUsers.add(user.toLowerCase());
                            numFollowers.add(0);
                        } else {
                            int indexofuser = followedUsers.indexOf(user.toLowerCase());
                            numFollowers.set(indexofuser, numFollowers.get(indexofuser) + 1);
                        }
                    }
                }
            }
        }

        //System.out.println(followedUsers.toString());
        String tempst = "";
        int tempint = 0;

        //sort two arrays
        for(int i = 0; i < numFollowers.size() + 1; i++){
            for(int j = 1; j < (numFollowers.size() - i); j++){
                if(numFollowers.get(j-1) > numFollowers.get(j)){
                    tempint = numFollowers.get(j-1);
                    numFollowers.set(j-1, numFollowers.get(j));
                    numFollowers.set(j, tempint);
                    tempst = followedUsers.get(j-1);
                    followedUsers.set(j-1, followedUsers.get(j));
                    followedUsers.set(j, tempst);
                }
            }
        }
        //System.out.println(numFollowers.toString());
        //System.out.println(followedUsers.toString());

        //now take k most followed users and add to list
        for(int i = 0; i < k; i++){
            mostFollowers.add(followedUsers.get(followedUsers.size() - i - 1));
        }

        return mostFollowers;
    }

    /**
     * Find all cliques in the social network.
     *
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     *
     * @return list of set of all cliques in the graph
     */
    public static List<Set<String>> findCliques(List<Tweets> tweets) {
        List<Set<String>> result = new ArrayList<Set<String>>();
        HashMap<String, List<String>> userMentions = new HashMap<>();               //for all users mentioned in text of tweet
        HashMap<String, List<String>> mentionsUser = new HashMap<>();               //for all times that a user shows up in text of tweet

        //make list of sets for who each user mentions and who mentions that user, keys will be username and then values
        //will be who they mention/who mentions them
        for(int i = 0; i < tweets.size(); i++){
            String currUser = tweets.get(i).getName().toLowerCase();
            String text = tweets.get(i).getText().toLowerCase();
            String[] arr = text.split("[ &^]");
            for(int j = 0; j < arr.length; j++){
                if(arr[j].length() > 0 && arr[j].charAt(0) == '@'){
                    String userMentioned = arr[j].substring(1);
                    if(!userMentioned.contains("@")) {
                        //make new key in userMentions if author has not mentioned anyone before
                        if(!userMentions.containsKey(currUser)){
                            List<String> shit = new ArrayList<>();
                            shit.add(userMentioned);
                            userMentions.put(currUser, shit);
                        }else if(!userMentions.get(currUser).contains(userMentioned)){
                            List<String> shit = userMentions.get(currUser);
                            shit.add(userMentioned);
                            userMentions.put(currUser, shit);
                        }
                        //make new key in mentionsUser if user mentioned has never been mentioned before
                        if(!mentionsUser.containsKey(userMentioned)){
                            List<String> hoe = new ArrayList<>();
                            hoe.add(currUser);
                            mentionsUser.put(userMentioned, hoe);
                        }else if(!mentionsUser.get(userMentioned).contains(currUser)){
                            List<String> hoe = mentionsUser.get(userMentioned);
                            hoe.add(currUser);
                            mentionsUser.put(userMentioned, hoe);
                        }
                    }
                }
            }
        }

        //now go through 2 hashMaps to cliques


        return result;
    }
}


