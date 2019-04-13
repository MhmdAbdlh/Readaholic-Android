package com.example.android.readaholic.contants_and_static_data;

/**
 * class holds
 */
public class Countries {
    public static String[] countries = new String[]{"Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Anguilla",
            "Antarctica",  "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas",
            "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia",
            "Bulgaria", "Burkina_Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape_Verde", "Cayman_Islands",
            "Central_African_Republic", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Congo",
            "Cook_Islands", "Costa_Rica", "Coted'Ivoire",
            "Croatia", "Cuba", "Cyprus", "Czech_Republic", "Denmark", "Djibouti", "Dominica",
            "East_Timor", "Ecuador", "Egypt", "El_Salvador", "Equatorial_Guinea", "Eritrea", "Estonia", "Ethiopia"
            ,"Faroe_Islands", "Fiji", "Finland", "France", "France_Metropolitan", "French_Guiana",
            "French_Polynesia", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar",
            "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea_Bissau", "Guyana", "Haiti",
            "Honduras", "Hong_Kong", "Hungary", "Iceland", "India",
            "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan",
            "Kazakhstan", "Kenya", "Kiribati", "Korea", "Kuwait",
            "Kyrgyzstan", "Lao", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya",
            "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia", "Madagascar",
            "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall_Islands", "Martinique", "Mauritania", "Mauritius",
            "Mayotte", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montserrat",
            "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands_Antilles",
            "New_Caledonia", "New_Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk_Island",
            "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua_New_Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn",
            "Poland", "Portugal", "Puerto_Rico", "Qatar", "Reunion", "Romania", "Russian_Federation", "Rwanda"
            , "Samoa", "San_Marino"
            , "Saudi Arabia", "Senegal", "Seychelles", "Sierra_Leone", "Singapore",
            "Slovakia", "Slovenia", "Solomon_Islands", "Somalia", "South_Africa"
            , "Spain", "Sri_Lanka",
            "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syrian",
            "Taiwan", "Tajikistan", "Thailand", "Togo", "Tokelau", "Tonga",
            "Tunisia", "Türkiye", "Turkmenistan" , "Tuvalu", "Uganda", "Ukraine",
            "United_Arab_Emirates", "United_Kingdom", "United_States", "Uruguay",
            "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam","Yemen", "Yugoslavia", "Zambia", "Zimbabwe"};

    public static int getCountryIndex(String Country){
        for (int i =0 ; i < countries.length ; i++)
        {
            if(countries[i].equals(Country)) {
                return i;
            }
        }
        return -1;
    }

}
