function validatePasswordFields(registrationForm){
    if(registrationForm.password !== registrationForm.passwordRepeated){
        return "passwords must match"
    }
    if(registrationForm.password === ""){
        return "password field must not be empty"
    }
    if(!isPasswordStrongEnough(registrationForm.password)){
        return "password is too weak"
    }
    return "good";
}
function isPasswordStrongEnough(password){
    let score = 0;
    if(password.length >= 8) score++;
    if(/[A_Z]/.test(password) && /[a-z]/.test(password)) score++;
    if(/[0-9]/.test(password)) score++;
    if(/[!@#$%^&*()_+{}\[\]:;<>,.?~\\/\-=|"']/.test(password)) score++;
    return score >= 2;
}

export {validatePasswordFields}