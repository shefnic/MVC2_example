var MIN_GROUP = 1;
var MAX_GROUP = 10;

var ALL_TOURS = [
    {val: "Gardiner_Lake",
     text: "Gardiner Lake",
     dur: [3, 5]
    },
    {val: "Hellroaring_Plateau",
     text: "Hellroaring Plateau",
     dur: [2, 3, 4]
    },
    {val: "The_Beaten_Path",
     text: "The Beaten Path",
     dur: [5, 7]
    }        
];

$(document).ready( function() {
    
    //Load party
    for(var i = MIN_GROUP; i <= MAX_GROUP; i++){
        $("#party").append($("<option>", {
            value: i,
            text: i
        }));
    }
    
    //Load tours
    for(var tour of ALL_TOURS){
        $("#tours").append($("<option>", {
            value: tour.val,
            text: tour.text
        }));
    }
    
    $("#tours option:eq(0)").attr("selected", "selected");
    loadDur();
    $("#tours").change(loadDur);
    
});

//Load Duration options dependant on Tour chosen
function loadDur(){
    
    $("#duration").find("option").remove();
    
    var dur = ALL_TOURS[$("#tours").prop("selectedIndex")].dur;
    
    for(var i = 0; i < dur.length; i++){
        $("#duration").append($("<option>", {
            value: dur[i],
            text: dur[i] + " days"
            }));
        }
}