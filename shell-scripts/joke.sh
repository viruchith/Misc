#!/bin/sh

# WARNING !!!!!!!!!!!!
# UNCENSORED VERSION..........
# EXPLICIT JOKES INCLUDED
#
CENSORED_ENDPT="https://v2.jokeapi.dev/joke/Any?blacklistFlags=nsfw,religious,political,racist,sexist,explicit"

UNCENSORED_ENDPT="https://v2.jokeapi.dev/joke/Any"

# USING UNCENSORED ENDPT !!!!!!

DATA=`curl -s $UNCENSORED_ENDPT`

TYPE=`echo $DATA | tr '\r\n' ' ' | jq -r ".type" `

ROFL_EMO="\xf0\x9f\xa4\xa3"

CLOWN_EMO="\xf0\x9f\xa4\xa1"

case $TYPE in

	single)
		JOKE=`echo $DATA | tr '\r\n' ' ' | jq -r ".joke"`

		printf "$CLOWN_EMO \033[1;33m $JOKE $ROFL_EMO \033[0m \n"

		;;

	twopart)

		SETUP=`echo $DATA | tr '\r\n' ' ' | jq -r ".setup"`

		DELIVERY=`echo $DATA | tr '\r\n' ' ' | jq -r ".delivery"`

		printf "$CLOWN_EMO \033[0;31m $SETUP \033[0m \n"

		sleep 1

		printf "\033[1;33m $DELIVERY $ROFL_EMO \033[0m \n"
		;;

	*)
		printf " Failed to entertain you !\n"
		;;
esac
