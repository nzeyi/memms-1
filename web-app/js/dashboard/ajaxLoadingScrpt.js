$(document).ready(function(){
  // load dashboard stuff here


var urlFromFacilityTypes=''

 $(":checkbox").change(function(){
        if($(this).attr("checked"))
        {
          var checkedBoxName=$(this).attr("name")
          var checkedBoxValue=$(this).val()

urlFromFacilityTypes="/memms/memmsReport/dashboard?targetURI=%2FmemmsReport%2Fdashboard&q="+checkedBoxName

alert(urlFromFacilityTypes)
//processDelete();

$.ajax({
    type: 'GET',
    url: urlFromFacilityTypes,
    success: function(data) {
        $('#equipment').val(data);
    }
});

        }
        else
        {
            //call the function to be fired
            //when your box changes from
            //checked to unchecked

        }
    });



  $('.equipment').click(function(e){
//alert("nanone");

e.preventDefault();
//First level of hierachie

 //$(".v-tabs-name v-tabs-fold-toggle").load($(this).attr('href'));
//second level of hierachie


var urll=$(this).attr('href')

$.ajax({
    type: 'GET',
    url: urll,
    success: function(data) {
        $('#equipment').val(data);
    }
});



    
  








/*
 $('#healthCenter').click(function(e){


alert("healthCenter");

});

$('#districtHospital').click(function(e){

alert("districtHospital");


});
$('#referralHospital').click(function(e){


alert("referralHospital");

});



*/


    //$("#equipment").load($(this).attr('href'));

 
});



});

function processDelete(){
    loacation.href="${createLink(action: 'deleteFiling')}";
}


