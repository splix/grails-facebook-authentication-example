package com.test

import com.the6hours.grails.springsecurity.facebook.FacebookUserDomain

import com.the6hours.example.User

class MyFacebookUser implements FacebookUserDomain {

	long uid
    String accessToken

	static belongsTo = [user: User]

	static constraints = {
		uid unique: true
	}
}
