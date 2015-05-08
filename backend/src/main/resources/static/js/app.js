require([
    'backbone',
    'views/app.view'
], function (Backbone, AppView) {
    /*jshint nonew:false*/
    Backbone.history.start();
    new AppView();
});
