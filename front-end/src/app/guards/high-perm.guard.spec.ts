import { TestBed, async, inject } from '@angular/core/testing';

import { HighPermGuard } from './high-perm.guard';

describe('HighPermGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [HighPermGuard]
    });
  });

  it('should ...', inject([HighPermGuard], (guard: HighPermGuard) => {
    expect(guard).toBeTruthy();
  }));
});
