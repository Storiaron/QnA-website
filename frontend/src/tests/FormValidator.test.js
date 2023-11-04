import { validatePasswordFields } from "../utils/FormValidator";

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