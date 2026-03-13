import { render, screen } from '@testing-library/react';
import { Navbar } from '../Navbar';
import { describe, it, expect } from 'vitest';
import { MemoryRouter } from 'react-router-dom';

describe('Navbar', () => {
  it('renders the logo', () => {
    render(
      <MemoryRouter initialEntries={['/']}>
        <Navbar />
      </MemoryRouter>
    );
    
    expect(screen.getByText('Sofkian')).toBeInTheDocument();
    expect(screen.getByText('OS')).toBeInTheDocument();
  });

  it('shows landing navigation links when not in app view', () => {
    render(
      <MemoryRouter initialEntries={['/']}>
        <Navbar />
      </MemoryRouter>
    );
    
    expect(screen.getByText('Cómo funciona')).toBeInTheDocument();
    expect(screen.getByText('Tecnología')).toBeInTheDocument();
    expect(screen.getByText('Acceder')).toBeInTheDocument();
  });

  it('shows only "Volver" when in app view', () => {
    render(
      <MemoryRouter initialEntries={['/kudos']}>
        <Navbar />
      </MemoryRouter>
    );
    
    expect(screen.queryByText('Cómo funciona')).not.toBeInTheDocument();
    expect(screen.getByText('Volver')).toBeInTheDocument();
  });

  it('shows app navigation links in history view', () => {
    render(
      <MemoryRouter initialEntries={['/kudos/history']}>
        <Navbar />
      </MemoryRouter>
    );

    expect(screen.getByText('Enviar Kudo')).toBeInTheDocument();
    expect(screen.getByText('Historial')).toBeInTheDocument();
    expect(screen.getByText('Volver')).toBeInTheDocument();
  });
});

