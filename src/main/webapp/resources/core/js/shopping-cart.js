
$(function () {

    var ajaxFormSubmit = function () {
        var $form = $(this);

        var options = {
            url: $form.attr("action"),
            type: $form.attr("method"),
            data: $form.serialize()
        };

        $.ajax(options).done(function (data) {
            var $target = $($form.attr("data-pf-target"));
            var $newHtml = $(data);
            $target.replaceWith($newHtml);
            $newHtml.effect("highlight");
        });

        return false;
    };

    var createAutocomplete = function () {
        var $input = $(this);

        var options = {
            source: $input.attr("data-pf-autocomplete"),
            select: submitAutocompleteForm
        };

        $input.autocomplete(options);
    };

    var submitAutocompleteForm = function (event, ui) {

        var $input = $(this);
        $input.val(ui.item.label);

        var $form = $input.parents("form:first");
        $form.submit();
    };

    var getPage = function () {

        var $a = $(this);

        var options = {
            url: $a.attr("href"),
            data: $("form").serialize(),
            type: "get"
        };

        $.ajax(options).done(function (data) {
            var target = "#transactionsList";
            $(target).replaceWith(data);
        });

        return false;
    };

    var susana = function () {

    	var $a = $(this);
    	
        console.log($a.attr("data-pf-url"));
        
        //Continue here!!!!!!!!

        return false;
    };
    
    $(".container").on("click", ".table a", susana);
});