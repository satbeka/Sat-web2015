<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 17.12.2014
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome MyWeb</title>
</head>
<body>

<header>
<div class="header">
<a href="/" class="logo"></a>
<div class="my_city">

<script type="text/javascript">
    jQuery(document).ready(function() {

        $(".choose_city_popup .regions a").click(function(){
            $(".choose_city_popup .regions li").removeClass("active");
            $(this).parent().addClass("active");
            return false;
        });

        $(".default_popup .close").click(function(){
            var $popup = $(this).parent();
            // делаем не активный элемент доработок
            if ($popup.attr('data-revised-sid') != null){
                var sid = $popup.attr('data-revised-sid');
                $('div.revised-item[data-sid='+sid+']').css('z-index', '0').css('position','static');
            }
            $popup.fadeOut(300);
            jQuery("#shade2").fadeOut(200);
            $('.station.active_st').removeClass('disabled');
            return false;
        });

        var get_showRegionPopup = $.cookie('show_region_popup');
        if(get_showRegionPopup == 1) {
            $("#regionsPopup").show();
        }

        jQuery(".choose_city_popup .regions a").click(function(){
            var iDistrictID = jQuery(this).prop("rel");
            jQuery(".subregions").hide();
            jQuery("#district_"+iDistrictID).show();
            return false;
        });


        // Выбор городов.
        jQuery(".subregions .region_link").click(function() {
            var iCityID = jQuery(this).prop("rel");
            var backURL = window.location.href;
            deleteCookie('pander', '/', '.shop.kz');
            if((window.location.href).match(/about\/shops\/\d*\//) || (window.location.href).match(/about\/shops\/cities\/\d*\//)) {
                backURL = '/about/shops/' + iCityID + '/';
            }
            var szUrl = '/index.php?action=changeSubRegion&regsectid='+iCityID+'&backurl='+backURL;

            window.location.href = szUrl;
            return false;
        });

        jQuery(".header_city_selector > a.selected").click(function() {
            jQuery(".choose_city_popup").toggle();
            jQuery(".shade2").fadeTo(200,0.7);
            if (window.PIE) {
                $('.choose_city_popup').each(function(){
                    PIE.detach(this);
                    PIE.attach(this);
                });
            }
            return false;
        });

        jQuery("#right_region").click(function() {
            if(get_showRegionPopup == 1) {
                $.cookie('show_region_popup', '0', {
                    expires : 0,
                    path    : '/',
                    domain  : window.location.host,
                    secure  : true
                });
            }
            console.log($.cookie('show_region_popup'));
            jQuery(this).parent().remove();
            return false;
        });

        jQuery("#wrong_region").click(function() {
            if(get_showRegionPopup == 1) {
                $.cookie('show_region_popup', '0', {
                    expires : 0,
                    path    : '/',
                    domain  : window.location.host,
                    secure  : true
                });
            }

            console.log($.cookie('show_region_popup'));
            jQuery(this).parent().remove();
            jQuery(".header_city_selector > a.selected").click();
            return false;
        });

        jQuery(".shade2").click(function() {
            if (jQuery(".choose_city_popup:visible").length > 0) {
                jQuery(".choose_city_popup:visible > a.close").click();
            }
        });
    });
</script>
<div class="header_city_selector">
    Ваш город: <a href="#" class="selected dotted">Алматы</a>
</div>
<div class="default_popup choose_city_popup" style="display: none;">
<a href="#" class="close"></a>
<div class="hd">Ваш город</div>
<div class="regions">
    <div class="hd">Область:</div>
    <ul>
        <li><a href="#" rel="192">Акмолинская область</a></li>
        <li><a href="#" rel="194">Актюбинская область</a></li>
        <li class="active"><a href="#" rel="195">Алматинская область</a></li>
        <li><a href="#" rel="196">Атырауская область</a></li>
        <li><a href="#" rel="197">Восточно-Казахстанская область</a></li>
        <li><a href="#" rel="198">Жамбылская область</a></li>
        <li><a href="#" rel="199">Западно-Казахстанская область</a></li>
        <li><a href="#" rel="200">Карагандинская область</a></li>
        <li><a href="#" rel="202">Костанайская область</a></li>
        <li><a href="#" rel="203">Кызылординская область</a></li>
        <li><a href="#" rel="204">Мангыстауская область</a></li>
        <li><a href="#" rel="205">Павлодарская область</a></li>
        <li><a href="#" rel="206">Северо-Казахстанская область</a></li>
        <li><a href="#" rel="211">Южно-Казахстанская область</a></li>
    </ul>
</div>
<div class="subregions" style="display:none;" id="district_192">
    <div class="column">
        <ul>
            <li><a class="region_link" rel="67" data-city="" href="#">Другой (Акмолинская обл.)</a></li>
            <li><a class="region_link" rel="247" data-city="" href="#">Астана (Автоматика)</a></li>
            <li><a class="region_link" rel="255" data-city="Астана (Караоткель 3)" href="#">Астана (Караоткель 3)</a></li>
            <li><a class="region_link" rel="249" data-city="" href="#">Астана (Кирова)</a></li>
            <li><a class="region_link" rel="250" data-city="" href="#">Астана (Коктал)</a></li>
            <li><a class="region_link" rel="241" data-city="" href="#">Астана (Мичурино)</a></li>
            <li><a class="region_link" rel="244" data-city="" href="#">Астана (новый Аэропорт)</a></li>
            <li><a class="region_link" rel="243" data-city="" href="#">Астана (Пригородный)</a></li>
            <li><a class="region_link" rel="245" data-city="" href="#">Астана (Промышленный)</a></li>
            <li><a class="region_link" rel="246" data-city="" href="#">Астана (ст.Сороковая)</a></li>
            <li><a class="region_link" rel="242" data-city="" href="#">Астана (Тельмана)</a></li>
            <li><a class="region_link" rel="248" data-city="" href="#">Астана (Уч.хоз)</a></li>
            <li><a class="region_link" rel="256" data-city="" href="#">Военгородок</a></li>
            <li><a class="region_link" rel="3" data-city="astana" href="#">Астана</a></li>
        </ul>
    </div>
    <div class="column">
        <ul>
            <li><a class="region_link" rel="27" data-city="" href="#">Атбасар</a></li>
            <li><a class="region_link" rel="30" data-city="" href="#">Боровое</a></li>
            <li><a class="region_link" rel="92" data-city="" href="#">Державинск</a></li>
            <li><a class="region_link" rel="32" data-city="" href="#">Ерейментау</a></li>
            <li><a class="region_link" rel="94" data-city="" href="#">Есиль</a></li>
            <li><a class="region_link" rel="8" data-city="kokshetau" href="#">Кокшетау</a></li>
            <li><a class="region_link" rel="98" data-city="" href="#">Макинск</a></li>
            <li><a class="region_link" rel="51" data-city="Stepnogorsk" href="#">Степногорск</a></li>
            <li><a class="region_link" rel="99" data-city="Stepnyak" href="#">Степняк</a></li>
            <li><a class="region_link" rel="61" data-city="shhuchinsk" href="#">Щучинск</a></li>
            <li><a class="region_link" rel="89" data-city="" href="#">Аршалы</a></li>
            <li><a class="region_link" rel="251" data-city="" href="#">Косщи</a></li>
            <li><a class="region_link" rel="63" data-city="Акколь" href="#">Акколь</a></li>
            <li><a class="region_link" rel="90" data-city="" href="#">Астраханка</a></li>
        </ul>
    </div>
    <div class="column">
        <ul>
            <li><a class="region_link" rel="91" data-city="" href="#">Балкашино</a></li>
            <li><a class="region_link" rel="93" data-city="" href="#">Егиндыколь</a></li>
            <li><a class="region_link" rel="95" data-city="" href="#">Жаксы</a></li>
            <li><a class="region_link" rel="258" data-city="Заозерное" href="#">Заозерное</a></li>
            <li><a class="region_link" rel="96" data-city="" href="#">Зеренда</a></li>
            <li><a class="region_link" rel="253" data-city="" href="#">Ильинка</a></li>
            <li><a class="region_link" rel="254" data-city="" href="#">Новоалександровка</a></li>
            <li><a class="region_link" rel="260" data-city="Чапаевское" href="#">Чапаевское</a></li>
            <li><a class="region_link" rel="100" data-city="" href="#">Шортанды</a></li>
        </ul>
    </div>
    <br clear="all">
</div>
<div class="subregions" style="display:none;" id="district_194">
    <div class="column">
        <ul>
            <li><a class="region_link" rel="68" data-city="otheractubinsk" href="#">Другой (Актюбинская обл.)</a></li>
            <li><a class="region_link" rel="10" data-city="Актобе" href="#">Актобе</a></li>
            <li><a class="region_link" rel="101" data-city="" href="#">Алга</a></li>
            <li><a class="region_link" rel="105" data-city="" href="#">Кандыагаш</a></li>
            <li><a class="region_link" rel="58" data-city="" href="#">Хромтау</a></li>
            <li><a class="region_link" rel="102" data-city="Бадамша" href="#">Бадамша</a></li>
            <li><a class="region_link" rel="106" data-city="" href="#">Карауылкелды</a></li>
            <li><a class="region_link" rel="107" data-city="Комсомольское" href="#">Комсомольское</a></li>
            <li><a class="region_link" rel="108" data-city="" href="#">Мартук</a></li>
            <li><a class="region_link" rel="109" data-city="" href="#">Ойыл</a></li>
        </ul>
    </div>
    <br clear="all">
</div>
<div class="subregions" id="district_195">
    <div class="column">
        <ul>
            <li><a class="region_link" rel="69" data-city="" href="#">Другой (Алматинская обл.)</a></li>
            <li><a class="region_link" rel="237" data-city="almaty" href="#">Алматы (Калкаман)</a></li>
            <li><b>Алматы</b></li>
            <li><a class="region_link" rel="38" data-city="" href="#">Капчагай</a></li>
            <li><a class="region_link" rel="39" data-city="" href="#">Каскелен</a></li>
            <li><a class="region_link" rel="122" data-city="Sarkand" href="#">Сарканд</a></li>
            <li><a class="region_link" rel="52" data-city="Talgar" href="#">Талгар</a></li>
            <li><a class="region_link" rel="53" data-city="Taldykorgan" href="#">Талдыкорган</a></li>
            <li><a class="region_link" rel="54" data-city="" href="#">Текели</a></li>
            <li><a class="region_link" rel="125" data-city="" href="#">Ушарал</a></li>
            <li><a class="region_link" rel="87" data-city="" href="#">Боралдай</a></li>
            <li><a class="region_link" rel="88" data-city="Отеген Батыр" href="#">Отеген Батыр</a></li>
            <li><a class="region_link" rel="123" data-city="Saryozek" href="#">Сарыозек</a></li>
            <li><a class="region_link" rel="238" data-city="Каменка" href="#">Каменка</a></li>
        </ul>
    </div>
    <br clear="all">
</div>
<div class="subregions" style="display:none;" id="district_196">
    <div class="column">
        <ul>
            <li><a class="region_link" rel="70" data-city="" href="#">Другой (Атырауская обл.)</a></li>
            <li><a class="region_link" rel="11" data-city="Атырау" href="#">Атырау</a></li>
            <li><a class="region_link" rel="42" data-city="" href="#">Кульсары</a></li>
            <li><a class="region_link" rel="131" data-city="" href="#">Махамбет</a></li>
            <li><a class="region_link" rel="130" data-city="" href="#">Макат</a></li>
            <li><a class="region_link" rel="55" data-city="" href="#">Тенгиз</a></li>
            <li><a class="region_link" rel="128" data-city="" href="#">Ганюшкино</a></li>
            <li><a class="region_link" rel="132" data-city="" href="#">Миялы</a></li>
        </ul>
    </div>
    <br clear="all">
</div>
<div class="subregions" style="display:none;" id="district_197">
    <div class="column">
        <ul>
            <li><a class="region_link" rel="71" data-city="" href="#">Другой (ВК обл.)</a></li>
            <li><a class="region_link" rel="37" data-city="" href="#">Зыряновск</a></li>
            <li><a class="region_link" rel="44" data-city="" href="#">Курчатов</a></li>
            <li><a class="region_link" rel="48" data-city="Ridder" href="#">Риддер</a></li>
            <li><a class="region_link" rel="20" data-city="Semipalatinsk" href="#">Семей</a></li>
            <li><a class="region_link" rel="13" data-city="ust-kamenogorsk" href="#">Усть-Каменогорск</a></li>
            <li><a class="region_link" rel="59" data-city="" href="#">Шемонаиха</a></li>
            <li><a class="region_link" rel="134" data-city="" href="#">Аягоз</a></li>
            <li><a class="region_link" rel="136" data-city="" href="#">Большенарымское</a></li>
            <li><a class="region_link" rel="142" data-city="" href="#">Кокпекты</a></li>
            <li><a class="region_link" rel="145" data-city="" href="#">Урджар</a></li>
        </ul>
    </div>
    <br clear="all">
</div>
<div class="subregions" style="display:none;" id="district_198">
    <div class="column">
        <ul>
            <li><a class="region_link" rel="72" data-city="" href="#">Другой (Жамбылская обл.)</a></li>
            <li><a class="region_link" rel="148" data-city="" href="#">Каратау</a></li>
            <li><a class="region_link" rel="14" data-city="taraz" href="#">Тараз</a></li>
            <li><a class="region_link" rel="156" data-city="" href="#">Шу</a></li>
            <li><a class="region_link" rel="43" data-city="" href="#">Курдай</a></li>
            <li><a class="region_link" rel="154" data-city="Sarykemer" href="#">Сарыкемер</a></li>
        </ul>
    </div>
    <br clear="all">
</div>
<div class="subregions" style="display:none;" id="district_199">
    <div class="column">
        <ul>
            <li><a class="region_link" rel="73" data-city="" href="#">Другой (ЗК обл.)</a></li>
            <li><a class="region_link" rel="22" data-city="" href="#">Аксай</a></li>
            <li><a class="region_link" rel="15" data-city="uralsk" href="#">Уральск</a></li>
            <li><a class="region_link" rel="157" data-city="" href="#">Жангала</a></li>
            <li><a class="region_link" rel="158" data-city="" href="#">Жанибек</a></li>
            <li><a class="region_link" rel="163" data-city="" href="#">Сайхин</a></li>
            <li><a class="region_link" rel="160" data-city="" href="#">Казталовка</a></li>
            <li><a class="region_link" rel="161" data-city="" href="#">Каратобе</a></li>
            <li><a class="region_link" rel="162" data-city="" href="#">Перемётное</a></li>
            <li><a class="region_link" rel="165" data-city="" href="#">Фёдоровка ЗКО</a></li>
            <li><a class="region_link" rel="167" data-city="" href="#">Чингирлау</a></li>
        </ul>
    </div>
    <br clear="all">
</div>
<div class="subregions" style="display:none;" id="district_200">
    <div class="column">
        <ul>
            <li><a class="region_link" rel="65" data-city="" href="#">Другой (Карагандинская обл.)</a></li>
            <li><a class="region_link" rel="82" data-city="Абай" href="#">Абай</a></li>
            <li><a class="region_link" rel="6" data-city="balhash" href="#">Балхаш</a></li>
            <li><a class="region_link" rel="5" data-city="jhezkazgan" href="#">Жезказган</a></li>
            <li><a class="region_link" rel="2" data-city="karagandy" href="#">Караганда</a></li>
            <li><a class="region_link" rel="172" data-city="" href="#">Каражал</a></li>
            <li><a class="region_link" rel="176" data-city="" href="#">Приозёрск</a></li>
            <li><a class="region_link" rel="80" data-city="Saran'" href="#">Сарань</a></li>
            <li><a class="region_link" rel="21" data-city="satpaev" href="#">Сатпаев</a></li>
            <li><a class="region_link" rel="4" data-city="temirtau" href="#">Темиртау</a></li>
            <li><a class="region_link" rel="83" data-city="Топар" href="#">Топар</a></li>
            <li><a class="region_link" rel="84" data-city="Шахтинск" href="#">Шахтинск</a></li>
            <li><a class="region_link" rel="263" data-city="Агадырь" href="#">Агадырь</a></li>
            <li><a class="region_link" rel="79" data-city="Актас" href="#">Актас</a></li>
        </ul>
    </div>
    <div class="column">
        <ul>
            <li><a class="region_link" rel="170" data-city="" href="#">Атасу</a></li>
            <li><a class="region_link" rel="171" data-city="" href="#">Ботакара</a></li>
            <li><a class="region_link" rel="33" data-city="" href="#">Жайрем</a></li>
            <li><a class="region_link" rel="175" data-city="" href="#">Осакаровка</a></li>
            <li><a class="region_link" rel="85" data-city="Шахан" href="#">Шахан</a></li>
            <li><a class="region_link" rel="81" data-city="Сортировка" href="#">Караганда (Сортировка)</a></li>
        </ul>
    </div>
    <br clear="all">
</div>
<div class="subregions" style="display:none;" id="district_202">
    <div class="column">
        <ul>
            <li><a class="region_link" rel="74" data-city="" href="#">Другой (Костанайская обл.)</a></li>
            <li><a class="region_link" rel="26" data-city="" href="#">Аркалык</a></li>
            <li><a class="region_link" rel="36" data-city="" href="#">Житикара</a></li>
            <li><a class="region_link" rel="17" data-city="kostanaj" href="#">Костанай</a></li>
            <li><a class="region_link" rel="45" data-city="" href="#">Лисаковск</a></li>
            <li><a class="region_link" rel="49" data-city="Rudnyy" href="#">Рудный</a></li>
            <li><a class="region_link" rel="183" data-city="" href="#">Затобольск</a></li>
            <li><a class="region_link" rel="185" data-city="" href="#">Карабалык</a></li>
            <li><a class="region_link" rel="188" data-city="" href="#">Сарыколь</a></li>
            <li><a class="region_link" rel="265" data-city="Тобол" href="#">Тобол</a></li>
            <li><a class="region_link" rel="180" data-city="" href="#">Аулиеколь</a></li>
            <li><a class="region_link" rel="181" data-city="" href="#">Боровской</a></li>
            <li><a class="region_link" rel="182" data-city="" href="#">Денисовка</a></li>
            <li><a class="region_link" rel="267" data-city="Ливановка" href="#">Ливановка</a></li>
        </ul>
    </div>
    <div class="column">
        <ul>
            <li><a class="region_link" rel="189" data-city="Taranovskoe" href="#">Тарановское</a></li>
            <li><a class="region_link" rel="192" data-city="" href="#">Узунколь</a></li>
            <li><a class="region_link" rel="193" data-city="" href="#">Фёдоровка</a></li>
        </ul>
    </div>
    <br clear="all">
</div>
<div class="subregions" style="display:none;" id="district_203">
    <div class="column">
        <ul>
            <li><a class="region_link" rel="75" data-city="" href="#">Другой (Казылординская обл.)</a></li>
            <li><a class="region_link" rel="195" data-city="" href="#">Аральск</a></li>
            <li><a class="region_link" rel="28" data-city="" href="#">Байконур</a></li>
            <li><a class="region_link" rel="16" data-city="kyzylorda" href="#">Кызылорда</a></li>
            <li><a class="region_link" rel="194" data-city="" href="#">Айтеке Би</a></li>
            <li><a class="region_link" rel="60" data-city="" href="#">Шиели</a></li>
        </ul>
    </div>
    <br clear="all">
</div>
<div class="subregions" style="display:none;" id="district_204">
    <div class="column">
        <ul>
            <li><a class="region_link" rel="76" data-city="" href="#">Другой (Мангыстауская обл.)</a></li>
            <li><a class="region_link" rel="12" data-city="aktau" href="#">Актау</a></li>
            <li><a class="region_link" rel="34" data-city="" href="#">Жана-Озен</a></li>
            <li><a class="region_link" rel="201" data-city="" href="#">Форт-Шевченко</a></li>
            <li><a class="region_link" rel="203" data-city="" href="#">Бейнеу</a></li>
        </ul>
    </div>
    <br clear="all">
</div>
<div class="subregions" style="display:none;" id="district_205">
    <div class="column">
        <ul>
            <li><a class="region_link" rel="66" data-city="" href="#">Другой (Павлодарская обл.)</a></li>
            <li><a class="region_link" rel="23" data-city="" href="#">Аксу</a></li>
            <li><a class="region_link" rel="7" data-city="pavlodar" href="#">Павлодар</a></li>
            <li><a class="region_link" rel="62" data-city="" href="#">Экибастуз</a></li>
            <li><a class="region_link" rel="206" data-city="" href="#">Баянаул</a></li>
            <li><a class="region_link" rel="268" data-city="Шидерты" href="#">Шидерты</a></li>
            <li><a class="region_link" rel="208" data-city="" href="#">Железинка</a></li>
            <li><a class="region_link" rel="209" data-city="" href="#">Кашыр</a></li>
            <li><a class="region_link" rel="211" data-city="" href="#">Успенка</a></li>
        </ul>
    </div>
    <br clear="all">
</div>
<div class="subregions" style="display:none;" id="district_206">
    <div class="column">
        <ul>
            <li><a class="region_link" rel="273" data-city="Чистополье" href="#">Чистополье</a></li>
            <li><a class="region_link" rel="77" data-city="" href="#">Другой (СК обл.)</a></li>
            <li><a class="region_link" rel="214" data-city="" href="#">Булаево</a></li>
            <li><a class="region_link" rel="18" data-city="petropavlovsk" href="#">Петропавловск</a></li>
            <li><a class="region_link" rel="220" data-city="Sergeevka" href="#">Сергеевка</a></li>
            <li><a class="region_link" rel="222" data-city="Taiynsha" href="#">Тайынша</a></li>
            <li><a class="region_link" rel="223" data-city="Talshik" href="#">Талшик</a></li>
            <li><a class="region_link" rel="217" data-city="" href="#">Новоишимское</a></li>
            <li><a class="region_link" rel="379" data-city="Рузаевка" href="#">Рузаевка</a></li>
            <li><a class="region_link" rel="219" data-city="" href="#">Саумалколь</a></li>
            <li><a class="region_link" rel="380" data-city="Тахтаброд" href="#">Тахтаброд</a></li>
            <li><a class="region_link" rel="224" data-city="" href="#">Тимирязево</a></li>
            <li><a class="region_link" rel="225" data-city="" href="#">Явленка</a></li>
        </ul>
    </div>
    <br clear="all">
</div>
<div class="subregions" style="display:none;" id="district_211">
    <div class="column">
        <ul>
            <li><a class="region_link" rel="78" data-city="" href="#">Другой (ЮК обл.)</a></li>
            <li><a class="region_link" rel="226" data-city="" href="#">Арыс</a></li>
            <li><a class="region_link" rel="227" data-city="" href="#">Жетысай</a></li>
            <li><a class="region_link" rel="40" data-city="" href="#">Кентау</a></li>
            <li><a class="region_link" rel="229" data-city="" href="#">Ленгер</a></li>
            <li><a class="region_link" rel="230" data-city="Saryagash" href="#">Сарыагаш</a></li>
            <li><a class="region_link" rel="56" data-city="" href="#">Туркестан</a></li>
            <li><a class="region_link" rel="19" data-city="Шымкент" href="#">Шымкент</a></li>
            <li><a class="region_link" rel="392" data-city="Казыгурт" href="#">Казыгурт</a></li>
            <li><a class="region_link" rel="275" data-city="Таукент" href="#">Таукент</a></li>
            <li><a class="region_link" rel="232" data-city="" href="#">Турар Рыскулов</a></li>
            <li><a class="region_link" rel="233" data-city="" href="#">Шардара</a></li>
            <li><a class="region_link" rel="236" data-city="" href="#">Шолаккорган</a></li>
        </ul>
    </div>
    <br clear="all">
</div>
<br clear="all">
</div>

<div id="regionsPopup" class="default_popup are_you_in_popup">
    <div class="suppose">Ваш регион Алматы?</div>
    <a href="#" id="right_region" class="default_button">Да</a>
    <a href="#" id="wrong_region" class="default_button">Нет</a>
    <br clear="all">
    <div class="nib"></div>
</div>			</div>

<div class="phone">
    <div class="phone_number">
        +7 (727) 356-52-90</div><div class="phone_number" style="background-image:none;">+7 771 006-62-30				</div>
    <div class="regime">Режим работы: 10:00 — 19:00</div>
    <a href="/about/shops/" class="address_and_contacts">Адреса и телефоны</a>
</div>

<div class="private">
<div class="personal">


    <style type="text/css">
        .b-popup {
            background: #FFF;
            background: -moz-linear-gradient(top, #ffffff 80%, #f7f7f7 100%);
            background: -webkit-gradient(linear, left top, left bottom, color-stop(80%,#FFF), color-stop(100%,#F7F7F7));
            background: -webkit-linear-gradient(top, #FFF 80%,#F7F7F7 100%);
            background: -o-linear-gradient(top, #ffffff 80%,#f7f7f7 100%);
            background: -ms-linear-gradient(top, #ffffff 80%,#f7f7f7 100%); 1
        background: linear-gradient(to bottom, #FFF 80%,#F7F7F7 100%);
            border:1px solid #797979;
            -webkit-border-radius:6px;
            -moz-border-radius:6px;
            border-radius:6px;
            position: fixed;
            z-index: 104;
            padding: 15px;
            display: none;
            -webkit-box-shadow: 0px 0px 25px 5px rgba(0, 0, 0, 0.5);
            -moz-box-shadow: 0px 0px 25px 5px rgba(0,0,0,0.5);
            box-shadow: 0px 0px 25px 5px rgba(0, 0, 0, 0.5);
            -moz-border-radius: 10px;
            -webkit-border-radius: 10px;
            border-radius: 10px;

            top: 115px;
            left: 50%;
            margin-left: -248px;
            padding: 40px 50px;
            width: 31em;
        }


        .b-ico_preloader_30x30{
            width:30px;
            height:30px;
            display: inline-block;
            background:url(/bitrix/templates/ww_3/images/preloader_30x30.gif) no-repeat
        }
        .b-popup__close {
            font-size:14px;
            line-height:1;
            color:#666;
            padding:10px 20px 0 0;
            float:right;
            cursor:pointer;
            white-space:nowrap;
            position:absolute;
            right:0;
            top:0;
            z-index:2;
            -webkit-border-radius:0 6px 0 0;
            -moz-border-radius:0 6px 0 0;
            border-radius:0 6px 0 0;
        }

        .b-popup__close .cross {
            font-family:arial;
            font-size:1.64em;
            line-height:1;
            color:#999;
            position:relative;
            left:.08em;
            top:-.06em;
            vertical-align:middle;
        }

        .b-popup__close:hover,
        .b-popup__close:hover .cross {
            color:red;
        }

        .b-popup .b-button {
            background: #ffc69e; /* Old browsers */
            background: -moz-linear-gradient(top,  #ffc69e 0%, #ff7f24 5%, #e36d19 100%); /* FF3.6+ */
            background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffc69e), color-stop(5%,#ff7f24), color-stop(100%,#e36d19)); /* Chrome,Safari4+ */
            background: -webkit-linear-gradient(top,  #ffc69e 0%,#ff7f24 5%,#e36d19 100%); /* Chrome10+,Safari5.1+ */
            background: -o-linear-gradient(top,  #ffc69e 0%,#ff7f24 5%,#e36d19 100%); /* Opera 11.10+ */
            background: -ms-linear-gradient(top,  #ffc69e 0%,#ff7f24 5%,#e36d19 100%); /* IE10+ */
            background: linear-gradient(to bottom,  #ffc69e 0%,#ff7f24 5%,#e36d19 100%); /* W3C */
            /*filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffc69e', endColorstr='#e36d19',GradientType=0 ); /* IE6-9 */
            border: #c95a10;

            -webkit-box-shadow: 0px 1px 4px 0px rgba(152, 70, 21, 0.5);
            box-shadow: 0px 1px 4px 0px rgba(152, 70, 21, 0.5);

            color: #fff;

            -webkit-border-radius: 4px;
            border-radius: 4px;

            text-shadow: 0px -1px 0px #753c1d;

            font-size: 14px;
            font-family: Arial, sans-serif;
            padding: 6px 12px;
            margin-right: .5em;

            border: 1px solid #c95a10;
        }

        .b-popup .b-button:hover {
            background: #ffc69e;
            background: -moz-linear-gradient(top,  #ffc69e 0%, #ff7f24 30%, #e36d19 100%);
            background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffc69e), color-stop(30%,#ff7f24), color-stop(100%,#e36d19));
            background: -webkit-linear-gradient(top,  #ffc69e 0%,#ff7f24 30%,#e36d19 100%);
            background: -o-linear-gradient(top,  #ffc69e 0%,#ff7f24 30%,#e36d19 100%);
            background: -ms-linear-gradient(top,  #ffc69e 0%,#ff7f24 30%,#e36d19 100%);
            background: linear-gradient(to bottom,  #ffc69e 0%,#ff7f24 30%,#e36d19 100%);
            /*filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffc69e', endColorstr='#e36d19',GradientType=0 ); */
        }

        .b-popup .b-button:active {
            -webkit-box-shadow: inset 0px 1px 6px 0px rgba(0, 0, 0, 0.2);
            box-shadow: inset 0px 1px 6px 0px rgba(0, 0, 0, 0.2);
            padding: 7px 12px 5px;
        }

        .b-popup .lnk {
            font-size: .85em;
            display: inline-block;
            zoom: 1;
            vertical-align: baseline;
            margin-right: .5em;
        }


    </style>

    <noindex>
        <div id="form_enter" class="b-popup" style="display: none;">
            <section>
                <div class="b-popup__close"><span class="text">Закрыть</span> <span class="cross">×</span></div>
                <div>


                    <form method="post" onsubmit="checkLogin(); return false;" action="/index.php?login=yes">
                        <h1>Вход</h1>
                        <input type="hidden" name="backurl" value="/index.php">
                        <input type="hidden" name="AUTH_FORM" value="Y">
                        <input type="hidden" name="TYPE" value="AUTH">


                        <div class="b-form_line">
                            <label class="title" for="enter_email">Логин</label>
                            <span class="input"><input type="text" id="enter_email" name="USER_LOGIN" maxlength="50" value=""></span>
                        </div>

                        <div class="b-form_line">
                            <label class="title" for="enter_password">Пароль</label>
                            <span class="input"><input type="password" id="enter_password" name="USER_PASSWORD" maxlength="50"></span>
                        </div>

                        <div class="b-form_line remember">
                            <div class="b-form_checkbox">
                                <input type="checkbox" id="enter_remember" name="USER_REMEMBER" value="Y" checked="checked">
                                <label for="enter_remember">Запомнить меня</label>
                            </div>
                        </div>

                        <div id="loginErrorDiv" class="b-form_line error-message" style="display: none;">
                            <b>Неверная комбинация логина и пароля</b> Проверьте состояние клавиши Caps Lock и текущую языковую раскладку
                        </div>

                        <div class="b-form_line button">
                            <i id="loginPreloader" class="b-ico b-ico_preloader_30x30" style="display: none;"></i>
                            <a id="loginButton" href="#" class="b-button submit" style="display: inline-block;">Войти</a>
                            <a rel="nofollow" class="lnk" href="/auth/?forgot_password=yes&amp;backurl=%2Findex.php">Забыли свой пароль?</a>
                        </div>


                    </form>
                </div>
                <div class="social_reg">
                </div>
            </section>
        </div>
    </noindex>


    <ul>
        <li><a rel="nofollow" href="do/login" class="login-button">Вход</a> в личный кабинет</li>
        <li><a rel="nofollow" href="/auth/index.php?register=yes">Регистрация</a></li>
    </ul>


</div>
<div class="basket">
    <ul>
        <li><a href="/personal/basket/">Ваша корзина</a></li>
        <li>
            <span class="empty">Нет товаров</span>
            <div class="full">
                <span class="goods_count"></span>
                <span class="summ"></span>
                <span class="tenge" style="display: none;">т</span>
            </div>
        </li>
    </ul>
</div>
</div>

<div class="h-social_net">
    <a title="Мы в Twitter" target="_blank" style="" href="https://twitter.com/shop_kz"><i class="s-twitter"></i></a>
    <a title="Мы в Facebook" target="_blank" style="" href="https://www.facebook.com/Shop.ww.kz"><i class="s-facebook"></i></a>
    <a title="Мы Вконтакте" target="_blank" style="" href="https://vk.com/ww_kz"><i class="s-vk"></i></a>
</div>



<div class="h_line"></div>

</div>
</header>






</body>
</html>
