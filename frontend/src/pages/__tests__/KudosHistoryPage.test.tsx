import { render, screen } from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import { beforeEach, describe, expect, it, vi } from 'vitest';
import KudosHistoryPage from '../KudosHistoryPage';

vi.mock('../../services', () => ({
  kudosService: {
    fetchKudos: vi.fn(),
  },
}));

import { kudosService } from '../../services';

describe('KudosHistoryPage', () => {
  beforeEach(() => {
    vi.clearAllMocks();
  });

  it('shows loading skeletons while fetching data', () => {
    vi.mocked(kudosService.fetchKudos).mockImplementationOnce(
      () => new Promise(() => {})
    );

    const { container } = render(
      <MemoryRouter>
        <KudosHistoryPage />
      </MemoryRouter>
    );

    expect(container.querySelectorAll('.animate-pulse').length).toBeGreaterThanOrEqual(4);
  });

  it('shows empty state when there are no kudos', async () => {
    vi.mocked(kudosService.fetchKudos).mockResolvedValueOnce([]);

    render(
      <MemoryRouter>
        <KudosHistoryPage />
      </MemoryRouter>
    );

    expect(await screen.findByText('Aun no hay Kudos enviados.')).toBeInTheDocument();
  });

  it('renders cards when kudos are returned', async () => {
    vi.mocked(kudosService.fetchKudos).mockResolvedValueOnce([
      {
        id: '1',
        fromUser: 'Alice',
        toUser: 'Bob',
        category: 'Teamwork',
        message: 'Great collaboration!',
        points: 10,
        createdAt: '2026-03-10T10:30:00',
      },
    ]);

    render(
      <MemoryRouter>
        <KudosHistoryPage />
      </MemoryRouter>
    );

    expect(await screen.findByText('Alice')).toBeInTheDocument();
    expect(screen.getByText('Bob')).toBeInTheDocument();
    expect(screen.getByText('Great collaboration!')).toBeInTheDocument();
  });

  it('shows error state when the API fails', async () => {
    vi.mocked(kudosService.fetchKudos).mockRejectedValueOnce(new Error('Boom'));

    render(
      <MemoryRouter>
        <KudosHistoryPage />
      </MemoryRouter>
    );

    expect(
      await screen.findByText('No pudimos cargar el historial de Kudos. Intenta nuevamente.')
    ).toBeInTheDocument();
  });
});
