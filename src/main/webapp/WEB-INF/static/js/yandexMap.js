ymaps.ready(init);

function init() {
    var groups = locations();
    var myMap = new ymaps.Map('map', {
            center: [51.661535, 39.200287],
            zoom: 10
        }, {
            searchControlProvider: 'yandex#search'
        }),
        menu = $('<ul class="menu"/>');

    for (var i = 0, l = groups.length; i < l; i++) {
        createMenuGroup(groups[i]);
    }

    function createMenuGroup(group) {
        var menuItem = $('<li class="liMenu"><h3 class="aMenuMap">' + group.name + '</h3></li>');
        var collection = new ymaps.GeoObjectCollection(null, {preset: group.style}),
            submenu = $('<ul class="submenu"/>');

        myMap.geoObjects.add(collection);
        menuItem
            .append(submenu)
            .appendTo(menu)
            .find('a')
            .bind('click', function () {
                if (collection.getParent()) {
                    myMap.geoObjects.remove(collection);
                    submenu.hide();
                } else {
                    myMap.geoObjects.add(collection);
                    submenu.show();
                }
            });
        for (var j = 0, m = group.items.length; j < m; j++) {
            createSubMenu(group.items[j], collection, submenu);
        }
    }

    function createSubMenu(group, collection, submenu) {
        var submenuItem = $('<li><a class="aSubmenuMap" href="#">' + group.name + '</a></li>'),
            placemark = new ymaps.Placemark(group.center, {balloonContent: group.name});

        collection.add(placemark);
        submenuItem
            .appendTo(submenu)
            .find('a')
            .bind('click', function () {
                if (!placemark.balloon.isOpen()) {
                    placemark.balloon.open();
                } else {
                    placemark.balloon.close();
                }
                return false;
            });
    }

    menu.appendTo($('body'));
    myMap.setBounds(myMap.geoObjects.getBounds());
}

function locations() {
    var groups = [];
    $.each(getLoadsAssignedToAuthenticationDriver(), function (i, item) {
        groups.push(
            {
                name: "Load: " + item.title,
                items:
                    [
                        {
                            center: [item.pickUpLocation.latitude, item.pickUpLocation.longitude],
                            name: "Pick up location: " + item.pickUpLocation.name
                        },
                        {
                            center: [item.deliveryLocation.latitude, item.deliveryLocation.longitude],
                            name: "Delivery location: " + item.deliveryLocation.name
                        }
                    ]

            }
        );
    });
    return groups;
}