This is a program that reads fictional "tweets" from a http web server - http://kevinstwitterclient2.azurewebsites.net/api/products

This program is able to filter the tweets by username, keyword and also return a list of tweets sent during a certain timespan.
Additionally, it can infer a user's social network and make cliques based on mutual friends and mentions.

compile command: javac -cp lib/jackson-annotations-2.10.0.jar:lib/jackson-core-2.10.0.jar:lib/jackson-databind-2.9.0.jar:lib/okhttp-3.9.0.jar:lib/okio-1.13.0.jar src/assignment4/Main.java src/assignment4/Filter.java src/assignment4/SocialNetwork.java src/assignment4/Timespan.java src/assignment4/TweetReader.java src/assignment4/Tweets.java -d bin

run command: java -cp bin:lib/jackson-annotations-2.10.0.jar:lib/jackson-core-2.10.0.jar:lib/jackson-databind-2.9.0.jar:lib/okhttp-3.9.0.jar:lib/okio-1.13.0.jar assignment4.Main
