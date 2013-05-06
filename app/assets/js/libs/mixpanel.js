/**Cookie Plugin v1.3
 * https://github.com/carhartl/jquery-cookie
 *
 * Copyright 2011, Klaus Hartl
 * Dual licensed under the MIT or GPL Version 2 licenses.
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.opensource.org/licenses/GPL-2.0
 */
(function ($, document, undefined) {

    var pluses = /\+/g;

    function raw(s) {
        return s;
    }

    function decoded(s) {
        return decodeURIComponent(s.replace(pluses, ' '));
    }

    var config = $.cookie = function (key, value, options) {

// write
        if (value !== undefined) {
            options = $.extend({}, config.defaults, options);

            if (value === null) {
                options.expires = -1;
            }

            if (typeof options.expires === 'number') {
                var days = options.expires, t = options.expires = new Date();
                t.setDate(t.getDate() + days);
            }

            value = config.json ? JSON.stringify(value) : String(value);

            return (document.cookie = [
                encodeURIComponent(key), '=', config.raw ? value : encodeURIComponent(value),
                options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
                options.path ? '; path=' + options.path : '',
                options.domain ? '; domain=' + options.domain : '',
                options.secure ? '; secure' : ''
            ].join(''));
        }

// read
        var decode = config.raw ? raw : decoded;
        var cookies = document.cookie.split('; ');
        for (var i = 0, l = cookies.length; i < l; i++) {
            var parts = cookies[i].split('=');
            if (decode(parts.shift()) === key) {
                var cookie = decode(parts.join('='));
                return config.json ? JSON.parse(cookie) : cookie;
            }
        }

        return null;
    };

    config.defaults = {};

    $.removeCookie = function (key, options) {
        if ($.cookie(key) !== null) {
            $.cookie(key, null, options);
            return true;
        }
        return false;
    };

})(jQuery, document);


function initialize_lazy_tracker() {
    $.cookie.json = true;
    var events = $.cookie("lazy_tracker_events");
    if( events && events.length && !window.lazy_tracker_send_lock ) {
        var evt = events[0];

        // track that an event is being sent to prevent `initialize_lazy_tracker` from sending duplicates
        window.lazy_tracker_send_lock = true;

        // track the event in FIFO pattern
        console.log("tracking", evt.event_name, evt.properties);
        mixpanel.track(evt.event_name, evt.properties, function() {

            // remove the first (this) event
            var events = $.cookie("lazy_tracker_events");
            events.splice(0,1);
            $.cookie("lazy_tracker_events", events);

            // mark that we are free to send another track event
            window.lazy_tracker_send_lock = false;

            // rinse, repeat
            initialize_lazy_tracker();
        });
    } else {
        window.lazy_tracker_active = false;
    }
}

function track_lazy(event_name, properties) {
    $.cookie.json = true;
    var events = $.cookie("lazy_tracker_events") || [];
    events.push({
        "event_name": event_name,
        "properties": properties
    });

    // store the events
    $.cookie("lazy_tracker_events", events);

    // wait a second, then track the event; this is to prevent events from even being started
    // when an immediate page change will occur
    setTimeout(initialize_lazy_tracker, 1000);
}

// start
initialize_lazy_tracker();
