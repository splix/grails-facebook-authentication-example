package com.the6hours.example

import com.the6hours.grails.springsecurity.facebook.FacebookUserDomain

import com.the6hours.example.User

class FacebookUser implements FacebookUserDomain {

	long uid
    String accessToken

	static belongsTo = [user: User]

	static constraints = {
		uid unique: true
	}
}
