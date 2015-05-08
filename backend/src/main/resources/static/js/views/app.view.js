/*global define*/
define([
    'jquery',
    'underscore',
    'backbone'
], function ($, _, Backbone) {
    'use strict';

    var AppView = Backbone.View.extend({

        el: '#content',

        initialize: function(){
            this.render();
        },

        render: function(){
            this.$el.html($("#message").val());
        }
    });

    return AppView;
});
