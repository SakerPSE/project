$(function(){
    $(".depth1 > li").mouseover(function(){
        $(this).find('.depth2').stop().slideDown(300);
    }).mouseleave(function(){
        $(this).find('.depth2').stop().slideUp(300);
    });

    var q_left = $('.menu').position().left+1200;
    var q_top = $('.menu').position().top;
    $('.quickMenu').css({
        'top':q_top,
        'left':q_left
    });

    function scroll() {
        
        //console.log($(window).scrollTop());
        var new_top = $(window).scrollTop()+q_top;
        var new_left = $('.menu').position().left+1200;
        // $('.quickMenu').css({
        //     'top':new_top
        // });
        $('.quickMenu').stop().animate({
            'top':new_top,
            'left':new_left
        },500);
    }
    $(window).scroll(function() {
        scroll();
    });
    $(window).resize(function() {
        scroll();
    });
    $(".board_title").click(function(){
        $('.board_title').removeClass('on');
        $(this).addClass('on');
        //console.log($(this).text());
        if ($(this).text()=='공지사항') {
            $('#board_notice').show();
            $('#board_data').hide();
        } else {
            $('#board_notice').hide();
            $('#board_data').show();
        }
    });
    $('#goTop').click(function(){
        $('html').animate({'scrollTop':0},200);
    });
});
