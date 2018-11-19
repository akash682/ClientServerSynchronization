osascript -e "tell app \"Terminal\"
do script \"cd $PWD && java -Djava.net.preferIPv4Stack=true -cp bin Assignment3.Process0\"
end  tell" &
osascript -e "tell app \"Terminal\"
do script \"cd $PWD && java -Djava.net.preferIPv4Stack=true -cp  bin Assignment3.Process1\"
end  tell" &
osascript -e "tell app \"Terminal\"
do script \"cd $PWD && java -Djava.net.preferIPv4Stack=true -cp bin Assignment3.Process2\"
end tell"

