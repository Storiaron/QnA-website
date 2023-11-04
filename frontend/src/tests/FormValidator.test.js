import { validateRegistrationForm } from "../FormValidator";

const passwordMatchingTests = [
    {
        registrationForm: { password: "1234", passwordRepeated: "1234"},
        expected: true
    },
    {
        registrationForm: { password: "testP", passwordRepeated: "testP"},
        expected: true
    },
    {
        registrationForm: { password: "1234", passwordRepeated: "123"},
        expected: false
    },
    {
        registrationForm: { password: "", passwordRepeated: "1234"},
        expected: false
    },
    {
        registrationForm: { password: "abcABC", passwordRepeated: "abcABc"},
        expected: false
    },
]
test.each(passwordMatchingTests)('password and re-enter password fields should have the same value', (registrationForm, expected) =>{
    expect(validateRegistrationForm(registrationForm)).toBe(expected);
})