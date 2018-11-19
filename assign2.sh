osascript -e "tell app \"Terminal\"
do script \"cd $PWD && java -Djava.net.preferIPv4Stack=true -cp bin Assignment2.Device1\"
end  tell" &
osascript -e "tell app \"Terminal\"
do script \"cd $PWD && java -Djava.net.preferIPv4Stack=true -cp  bin Assignment2.Device2\"
end  tell" &
osascript -e "tell app \"Terminal\"
do script \"cd $PWD && java -Djava.net.preferIPv4Stack=true -cp bin Assignment2.Device3\"
end tell"
