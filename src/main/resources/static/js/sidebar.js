//
// Sidebar
//
// When the sidebar is hidden, we apply the inert attribute to prevent focus from reaching it. Due to the many states
// the sidebar can have (e.g. static, hidden, expanded), we test for visibility by checking to see if it's placed
// offscreen or not. Then, on resize/transition we make sure to update the attribute accordingly.
//
(() => {
    function getSidebar() {
        return document.getElementById('sidebar');
    }

    function isSidebarOpen() {
        return document.documentElement.classList.contains('sidebar-open');
    }

    function isSidebarVisible() {
        return getSidebar().getBoundingClientRect().x >= 0;
    }

    function toggleSidebar(force) {
        const isOpen = typeof force === 'boolean' ? force : !isSidebarOpen();
        return document.documentElement.classList.toggle('sidebar-open', isOpen);
    }

    function updateInert() {
        getSidebar().inert = !isSidebarVisible();
    }

    // Toggle the menu
    document.addEventListener('click', event => {
        const menuToggle = event.target.closest('#menu-toggle');
        if (!menuToggle) return;
        toggleSidebar();
    });

    // Update the sidebar's inert state when the window resizes and when the sidebar transitions
    window.addEventListener('resize', () => toggleSidebar(false));

    document.addEventListener('transitionend', event => {
        const sidebar = event.target.closest('#sidebar');
        if (!sidebar) return;
        updateInert();
    });

    // Close when a menu item is selected on mobile
    document.addEventListener('click', event => {
        const sidebar = event.target.closest('#sidebar');
        const link = event.target.closest('a');
        if (!sidebar || !link) return;

        if (isSidebarOpen()) {
            toggleSidebar();
        }
    });

    // Close when open and escape is pressed
    document.addEventListener('keydown', event => {
        if (event.key === 'Escape' && isSidebarOpen()) {
            event.stopImmediatePropagation();
            toggleSidebar();
        }
    });

    // Close when clicking outside of the sidebar
    document.addEventListener('mousedown', event => {
        if (isSidebarOpen() & !event.target?.closest('#sidebar, #menu-toggle')) {
            event.stopImmediatePropagation();
            toggleSidebar();
        }
    });

    updateInert();
})();
