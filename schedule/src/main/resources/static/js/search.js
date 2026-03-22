document.addEventListener("DOMContentLoaded", function () {
    const searchInput = document.querySelector('#searchField input');
    const suggestionsContainer = document.getElementById('suggestionsContainer');
    const actions = document.getElementById('actions');
    const searchForm = document.querySelector('.search-form');
    const searchToggle = document.getElementById('searchToggle');
    const closeSearch = document.getElementById('closeSearch');

    if (!searchInput || !suggestionsContainer || !searchForm || !actions) {
        console.error("DOM не найден");
        return;
    }

    let debounceTimer;

    if (searchToggle) {
        searchToggle.addEventListener('click', () => {
            actions.classList.add('open');
            searchInput.focus();
        });
    }

    if (closeSearch) {
        closeSearch.addEventListener('click', () => {
            actions.classList.remove('open');
            suggestionsContainer.classList.remove('active');
        });
    }

    function fetchSuggestions(query) {
        if (query.length < 2) {
            suggestionsContainer.classList.remove('active');
            return;
        }

        fetch(`/api/suggestions?query=${encodeURIComponent(query)}&limit=10`, {
            credentials: "include"
        })
            .then(res => res.json())
            .then(data => {
                renderSuggestions(data);
                suggestionsContainer.classList.toggle('active', data.length > 0);
            })
            .catch(err => console.error('API error:', err));
    }

    function renderSuggestions(suggestions) {
        suggestionsContainer.innerHTML = '';

        suggestions.forEach(s => {
            const div = document.createElement('div');
            div.className = 'suggestion-item';
            div.innerHTML = `<span class="suggestion-type">${s.type}</span> <span>${s.label}</span>`;

            div.addEventListener('click', () => {
                searchInput.value = s.value;
                suggestionsContainer.classList.remove('active');
                searchForm.submit();
            });

            suggestionsContainer.appendChild(div);
        });
    }

    searchInput.addEventListener('input', function () {
        clearTimeout(debounceTimer);
        const query = this.value.trim();

        if (query.length >= 2) {
            debounceTimer = setTimeout(() => fetchSuggestions(query), 300);
        } else {
            suggestionsContainer.classList.remove('active');
        }
    });

    document.addEventListener('click', function (e) {
        if (!searchInput.contains(e.target) && !suggestionsContainer.contains(e.target)) {
            suggestionsContainer.classList.remove('active');
        }
    });

    searchInput.addEventListener('focus', function () {
        const query = this.value.trim();
        if (query.length >= 2) {
            fetchSuggestions(query);
        }
    });
});