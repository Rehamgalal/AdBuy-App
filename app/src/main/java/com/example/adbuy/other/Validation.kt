package com.example.adbuy.other

import android.text.InputFilter
import android.util.Patterns
import android.widget.EditText

object Validation {

    fun validateLoginInput(
        email: EditText,
        password: EditText
    ): Boolean {
        password.filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
            source.toString().filterNot {
                it.isWhitespace()
            }
        })
        if (email.text.isEmpty()) {

            return false
        }
        if (password.text.isEmpty()) {

            return false
        }
        if (!email.text.trim().isValidEmail()) {
            return false
        }
        if (password.text.count { it.isDigit() } < 8) {

            return false
        }

        return true
    }

    fun validateLoginInputError(
        email: EditText,
        password: EditText
    ) {
        password.filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
            source.toString().filterNot {
                it.isWhitespace()
            }
        })

        if (password.text.isEmpty()) {
            password.error = "Password is empty"

        }
        if (!email.text.trim().isValidEmail()) {
            email.error = "Email is not valid"
        }
        if (email.text.isEmpty()) {
            email.error = "Email is empty"

        }
        if (password.text.count { it.isDigit() } < 7) {
            password.error = "Password should be 8 or more characters"

        }

    }

    fun validateRegistrationInput(
        firstname: EditText,
        lastname: EditText,
        email: EditText,
        password: EditText,
        confirmedPassword: EditText,
        mobile: EditText
    ): Boolean {
        if (firstname.text.isEmpty()) {
            return false

        }
        if (lastname.text.isEmpty()) {
            return false

        }
        if (!email.text.isValidEmail()) {
            return false
        }
        if (email.text.isEmpty()) {
            return false

        }
        if (password.text.isEmpty()) {
            return false
        }
        if (confirmedPassword.text.isEmpty()) {
            return false
        }

        if (password.text.toString() != confirmedPassword.text.toString()) {
            return false
        }
        if (password.text.count { it.isDigit() } < 7) {
            return false
        }
        if (confirmedPassword.text.count { it.isDigit() } < 7) {
            return false
        }
        return true
    }

    fun validateRegistrationInputError(
        firstname: EditText,
        lastname: EditText,
        email: EditText,
        password: EditText,
        confirmedPassword: EditText,
        mobile: EditText
    ) {
        if (firstname.text.isEmpty()) {
            firstname.error = "firstname is empty"

        }
        if (lastname.text.isEmpty()) {
            lastname.error = "lastname is empty"


        }
        if (!email.text.trim().isValidEmail()) {
            email.error = "email is not valid"
        }
        if (email.text.isEmpty()) {
            email.error = "email is empty"


        }
        if (password.text.isEmpty()) {
            password.error = "password is empty"
        }
        if (confirmedPassword.text.isEmpty()) {
            confirmedPassword.error = "confirmedPassword is empty"

        }

        if (password.text.toString() != confirmedPassword.text.toString()) {
            password.error = " password is not matching with comfirmed password"
            confirmedPassword.error = " confirmedPassword is not matching with  password"

        }
        if (password.text.count { it.isDigit() } < 8) {
            password.error = "Password should be 8 or more characters"

        }
        if (confirmedPassword.text.count { it.isDigit() } < 8) {
            confirmedPassword.error = "Password should be 8 or more characters"
        }
        if (mobile.text.isEmpty()) {
            mobile.error = "Mobile is empty"
        }
        if (mobile.text.count { it.isDigit() } < 11 || mobile.text.count { it.isDigit() } > 11) {
            mobile.error = "Password should be 11 or more characters"

        }
    }

    fun CharSequence?.isValidEmail() =
        !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()


}