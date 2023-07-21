import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ScIdService {
  private scId: string | null = null;

  setScId(scId: string) {
    this.scId = scId;
  }

  getScId(): string | null {
    return this.scId;
  }
}
