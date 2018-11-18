osascript -e 'tell app "Terminal"
do script "java -Djava.net.preferIPv4Stack=true -cp /Users/sohilshrestha/Desktop/LogicalClock1/bin assignment1.App"
end  tell' &
osascript -e 'tell app "Terminal"
do script "java -Djava.net.preferIPv4Stack=true -cp /Users/sohilshrestha/Desktop/LogicalClock1/bin assignment1.P2" 
end  tell' &
osascript -e 'tell app "Terminal" 
do script "java -Djava.net.preferIPv4Stack=true -cp /Users/sohilshrestha/Desktop/LogicalClock1/bin assignment1.P3"
end tell'