function timeDifferenceCalculator(timestr){
    const YEAR = 31536000000, MONTH = 2678400000, DAY = 86400000, HOUR = 3600000, MINUTE = 60000;
    const time = new Date(timestr).getTime();
    const currentTime = new Date().getTime();
    const timeDifference = currentTime-time;
    if(timeDifference < 0){
        return "something went wrong"
    }
    if(timeDifference > YEAR){
        return "more than a year ago"
    }
    if(timeDifference > MONTH){
        return "more than a month ago"
    }
    const dayDifference = Math.floor(timeDifference / DAY);
    if(dayDifference > 7){
        return "more than a week ago"
    }
    if(dayDifference > 1){
        return `${dayDifference} days ago`
    } 
    if(dayDifference === 1){
        return "1 day ago"
    }
    const hourDifference = Math.floor(timeDifference / HOUR); 
    if(hourDifference > 1){
        return `${hourDifference} hours ago`
    }
    if(hourDifference === 1){
        return "1 hour ago"
    }
    const minuteDifference = Math.floor(timeDifference / MINUTE);
    if(minuteDifference > 1){
        return `${minuteDifference} minutes ago`
    }
    return "now"
}
export default timeDifferenceCalculator;