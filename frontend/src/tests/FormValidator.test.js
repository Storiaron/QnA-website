import { validatePasswordFields, isEmailValid } from "../utils/FormValidator";

const passwordMatchingTests = [
    {
        registrationForm: { password: "12&34Aa", passwordRepeated: "12&34Aa"},
        expected: "good"
    },
    {
        registrationForm: { password: "testP1aaaaaaaa", passwordRepeated: "testP1aaaaaaaa"},
        expected: "good"
    },
    {
        registrationForm: { password: "1234", passwordRepeated: "123"},
        expected: "passwords must match"
    },
    {
        registrationForm: { password: "", passwordRepeated: "1234"},
        expected: "passwords must match"
    },
    {
        registrationForm: { password: "abcABC", passwordRepeated: "abcABc"},
        expected: "passwords must match"
    },
    {
        registrationForm: { password: "", passwordRepeated: ""},
        expected: "password field must not be empty"
    },
    {
        registrationForm: { password: "1234", passwordRepeated: "1234"},
        expected: "password is too weak"
    },
    {
        registrationForm: { password: "abc", passwordRepeated: "abc"},
        expected: "password is too weak"
    },
    {
        registrationForm: { password: "ABC", passwordRepeated: "ABC"},
        expected: "password is too weak"
    },
]
test.each(passwordMatchingTests)('password  field validations', ({registrationForm, expected}) =>{
    expect(validatePasswordFields(registrationForm)).toEqual(expected);
})
const emailTests = [
    {
        email: "",
        expected: false
    },
    {
        email: "1234@",
        expected: false
    },
    {
        email: "abc@gmail@.com",
        expected: false
    },
    {
        email: "abc@gmail.commmm",
        expected: false
    },
    {
        email: "abc@gmail.comm",
        expected: true
    },
    {
        email: "abc@gmail.de",
        expected: true
    },
]
test.each(emailTests)('email tests', ({email, expected}) =>{
    expect(isEmailValid(email)).toEqual(expected);
})